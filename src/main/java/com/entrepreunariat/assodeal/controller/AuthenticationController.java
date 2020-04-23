package com.entrepreunariat.assodeal.controller;
import com.entrepreunariat.assodeal.config.jwt.JwtTokenUtil;
import com.entrepreunariat.assodeal.dao.ConfirmationTokenRepository;
import com.entrepreunariat.assodeal.model.User;
import com.entrepreunariat.assodeal.model.dto.UserDTO;
import com.entrepreunariat.assodeal.model.jwt.ConfirmationToken;
import com.entrepreunariat.assodeal.model.jwt.JwtResponse;
import com.entrepreunariat.assodeal.service.EmailSenderService;
import com.entrepreunariat.assodeal.service.JwtUserDetailsService;
import com.entrepreunariat.assodeal.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import springfox.documentation.annotations.ApiIgnore;

import java.text.ParseException;
import java.util.Date;

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

    @Value("${url.address}")
    private String url;

    @Value("${spring.mail.username}")
    private String mailContact;

    @Value("${spring.mail.subject}")
    private String mailSubject;

    @Value("${spring.mail.text}")
    private String mailText;

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
        User existingUser = userDetailsService.findExistingMailOrUsername(userDTO.getMail(), userDTO.getUsername());

        if (existingUser != null) {
            LOGGER.error("L'email ou le pseudo exite déjà");
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        } else if (userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            userDTO.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
            userDTO.setPassword(bcryptEncoder.encode(userDTO.getConfirmPassword()));
            User user = userDetailsService.save(userDTO);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(convertUserToEntity(userDTO).getMail());
            mailMessage.setSubject(mailSubject);
            mailMessage.setFrom(mailContact);
            mailMessage.setText(mailText
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

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.POST})
    @ApiIgnore
    public ResponseEntity<?> confirmUserAccountPost(@RequestParam("token") String confirmationToken) throws Exception {
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.CREATED);
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            if (token.getUser().isEnabled()) {
                response = new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                User user = userDetailsService.findExistingMailOrUsername(token.getUser().getMail(), token.getUser().getUsername());
                if(user!=null) {
                    user.setEnabled(true);
                    UserDTO userDTO = convertEntityToDTO(user);
                    userDetailsService.save(userDTO);
                }
                else{
                    response = new ResponseEntity<>(HttpStatus.CONFLICT);
                }
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
