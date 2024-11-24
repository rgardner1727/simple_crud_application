package com.rgardner.simple_crud_application.home;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(ModelMap modelMap, HttpSession httpSession, Authentication authentication){
        httpSession.setAttribute("username", authentication.getName());
        modelMap.addAttribute("username", httpSession.getAttribute("username"));
        return "home";
    }
}
