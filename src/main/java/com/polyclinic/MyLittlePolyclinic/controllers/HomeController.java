package com.polyclinic.MyLittlePolyclinic.controllers;

import com.polyclinic.MyLittlePolyclinic.services.DoctorsService;
import com.polyclinic.MyLittlePolyclinic.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final DoctorsService doctorsService;
    private final UserService userService;

    @GetMapping("/")
    public String home(@RequestParam(name = "specialization", required = false) String specialization, Principal principal, Model model) {
        model.addAttribute("doctors", doctorsService.listDoctors(specialization));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "home";
    }
}
