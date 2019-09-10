package com.entrepreunariat.assodeal.controller;

import com.entrepreunariat.assodeal.model.User;
import com.entrepreunariat.assodeal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserService userService;

    /*@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
*/
    @GetMapping("/index")
    public String login(final Model model, @ModelAttribute User user) {
        return "index";
    }

    @RequestMapping(value = "/registration", method = {RequestMethod.POST})
    public String registration(final Model model, @ModelAttribute User user) {
        User lastUser = userService.findLastUser();
        user.setIdUser(lastUser.getIdUser()+1);
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "redirect:/registration";
        }
        //user.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        userService.saveUser(user);
        return "redirect:/produits";
    }

}
