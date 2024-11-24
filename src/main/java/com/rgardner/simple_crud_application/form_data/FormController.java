package com.rgardner.simple_crud_application.form_data;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String getRegisterPage(ModelMap modelMap, @RequestParam(value="error", required = false) String error){
        if(error != null)
            modelMap.addAttribute("errorMessage", "User this username already exists.");
        modelMap.addAttribute("formData", new FormData());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute FormData formData){
        try {
            UserDetails userDetails = User.withUsername(formData.getUsername())
                    .password(formData.getPassword())
                    .passwordEncoder(s -> passwordEncoder.encode(s))
                    .roles("USER")
                    .build();
            jdbcUserDetailsManager.createUser(userDetails);
        }catch(Exception e){
            return "redirect:/register?error=true";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, @RequestParam(value="error", required = false) String error, ModelMap modelMap){
        if(authentication != null && authentication.isAuthenticated())
            return "redirect:/";
        else {
            if(error != null)
                modelMap.addAttribute("errorMessage", "Invalid username or password.");
            return "login";
        }
    }
}
