package ru.itis.springbootdemo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;

@Controller
public class ProfileController {
    @GetMapping("/profile")
    private String getProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
        String email = userDetails.getUsername();
        model.addAttribute("email", email);
        return "profile_page";
    }
}
