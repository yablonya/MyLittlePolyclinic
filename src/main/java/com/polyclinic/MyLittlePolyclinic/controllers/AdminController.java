package com.polyclinic.MyLittlePolyclinic.controllers;

import com.polyclinic.MyLittlePolyclinic.services.DoctorsService;
import com.polyclinic.MyLittlePolyclinic.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final DoctorsService doctorsService;

    @GetMapping("/admin")
    public String admin(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("doctors", doctorsService.doctorList());
        model.addAttribute("users", userService.usersList(name));
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/unban/{id}")
    public String userUnBan(@PathVariable("id") Long id) {
        userService.unbanUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/role/{id}")
    public String changeUserRoles(@PathVariable("id") Long id) {
        userService.changeUserRoles(id);
        return "redirect:/admin";
    }
}
