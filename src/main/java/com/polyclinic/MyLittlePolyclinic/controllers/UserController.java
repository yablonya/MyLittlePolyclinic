package com.polyclinic.MyLittlePolyclinic.controllers;

import com.polyclinic.MyLittlePolyclinic.models.User;
import com.polyclinic.MyLittlePolyclinic.services.DoctorsService;
import com.polyclinic.MyLittlePolyclinic.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final DoctorsService doctorsService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMassage", "Користувач з email: " + user.getEmail() + " вже існує");
            return "registration";
        }
        userService.createUser(user);
        return "redirect:/login";
    }
    @GetMapping("/user-account")
    public String userAccount(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("doctors", doctorsService.listDoctors(null));
        return "user-account";
    }

    @PostMapping("/user/add-admission")
    public String addAdmission(@RequestParam("time") String admission, Principal principal) {
        Long id = Long.parseLong(admission);
        userService.addAdmissionToUser(principal, id);
        return "success-admission";
    }

    @PostMapping("/user/remove-admission/{id}")
    public String removeAdmission(@PathVariable("id") Long id, Principal principal) {
        userService.removeAdmissionFromUser(principal, id);
        return "redirect:/user-account";
    }
}
