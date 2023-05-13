package com.polyclinic.MyLittlePolyclinic.controllers;

import com.polyclinic.MyLittlePolyclinic.models.Doctor;
import com.polyclinic.MyLittlePolyclinic.services.DoctorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorsService doctorsService;

    @GetMapping("/")
    public String home(@RequestParam(name = "specialization", required = false) String specialization, Model model) {
        model.addAttribute("doctors", doctorsService.listDoctors(specialization));
        return "home";
    }

    @GetMapping("/doctor/{id}")
    public String doctorSchedule(@PathVariable Long id, Model model) {
        Doctor doctor = doctorsService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("image", doctor.getPhoto());
        return "doctor-schedule";
    }

    @PostMapping("/doctor/add")
    public String addDoctor(@RequestParam("file") MultipartFile file, Doctor doctor) throws IOException {
        doctorsService.addDoctor(doctor, file);
        return "redirect:/";
    }

    @PostMapping("/doctor/remove/{id}")
    public String removeDoctor(@PathVariable Long id) {
        doctorsService.removeDoctor(id);
        return "redirect:/";
    }
}