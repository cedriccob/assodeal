package com.entrepreunariat.assodeal.controller;

import com.entrepreunariat.assodeal.config.jwt.JwtTokenUtil;
import com.entrepreunariat.assodeal.dao.ConfirmationTokenRepository;
import com.entrepreunariat.assodeal.model.Role;
import com.entrepreunariat.assodeal.model.User;
import com.entrepreunariat.assodeal.model.dto.ApplicationDTO;
import com.entrepreunariat.assodeal.model.dto.UserDTO;
import com.entrepreunariat.assodeal.model.jwt.ConfirmationToken;
import com.entrepreunariat.assodeal.model.jwt.JwtRequest;
import com.entrepreunariat.assodeal.model.jwt.JwtResponse;
import com.entrepreunariat.assodeal.service.EmailSenderService;
import com.entrepreunariat.assodeal.service.JwtUserDetailsService;
import com.entrepreunariat.assodeal.service.RoleService;
import com.entrepreunariat.assodeal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(value = "authentication", description = "Opérations pour l'authentification permettant d'effectuer les actions sur les API")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    private RoleService roleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @ApiOperation(value = "Obtention du token pour accéder à toutes les API")
    @RequestMapping(value = "/authenticate/swagger", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationTokenForSwagger(@RequestParam("password") String password,
                                                                 @RequestParam("username") String username) throws Exception {

        authenticate(username, password);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiIgnore
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) throws Exception {
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.OK);
        User existingUser = userDetailsService.findExistingMail(userDTO.getMail());

        if (existingUser != null) {
            LOGGER.error("L'email existe déjà");
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        } else if (userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            userDTO.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
            userDTO.setPassword(bcryptEncoder.encode(userDTO.getConfirmPassword()));
            User user = userDetailsService.save(userDTO);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(convertUserToEntity(userDTO).getMail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("contact-assodeal@gmail.com");
            mailMessage.setText("To confirm your account, please click here : http://"
                    + InetAddress.getLoopbackAddress().getHostName() + ":" + serverPort + "/confirm-account?token="
                    + confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
        } else {
            LOGGER.error("Les mots de passe sont différents");
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @ApiIgnore
    @ApiOperation(value = "Obtention du token pour accéder à toutes les API")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO userDTO) throws Exception {
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        authenticate(userDTO.getUsername(), userDTO.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
        if (userDetails.isEnabled()) {
            final String token = jwtTokenUtil.generateToken(userDetails);
            User user = userDetailsService.findUserByUsername(userDetails.getUsername());
            user.setDateDernierLogin(new Date());
            UserDTO dto = convertEntityToDTO(user);
            userDetailsService.save(dto);
            response = ResponseEntity.ok(new JwtResponse(token));
        }
        return response;
    }


    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET})
    @ApiIgnore
    public RedirectView confirmUserAccount(@RequestParam("token") String confirmationToken) throws Exception {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://" + InetAddress.getLoopbackAddress().getHostName() + "/assodeal/controller/controller.php?token=" + confirmationToken);
        return redirectView;
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.POST})
    @ApiIgnore
    public ResponseEntity<?> confirmUserAccountPost(@RequestParam("token") String confirmationToken) throws Exception {
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.CREATED);
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            if (token.getUser().isEnabled()) {
                response = new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                User user = userDetailsService.findExistingMail(token.getUser().getMail());
                user.setEnabled(true);
                UserDTO userDTO = convertEntityToDTO(user);
                userDetailsService.save(userDTO);
            }
        } else {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    private User convertUserToEntity(UserDTO userDTO) throws ParseException {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertEntityToDTO(User user) throws ParseException {
        return modelMapper.map(user, UserDTO.class);
    }

}
