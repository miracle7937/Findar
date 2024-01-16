package com.findar.findar.controller;

import com.findar.findar.models.RegistrationForm;
import com.findar.findar.models.User;
import com.findar.findar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register2";
    }

    @PostMapping("/register")
    public String registerUser(RegistrationForm registrationForm, Model model) {

        if (!registrationForm.getConfirmPassword().equals(registrationForm.getPassword())) {
            model.addAttribute("errorMessage", "Please password must be match.");
            return "register2";
        }else {
            User user = new User();
            System.out.println(registrationForm.toString());
         user.setUsername(registrationForm.getUsername());
        user.setPassword(registrationForm.getPassword());
        userService.registerUser(user);
            return "redirect:/login";
        }


    }
}
