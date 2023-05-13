package com.polyclinic.MyLittlePolyclinic.services;

import com.polyclinic.MyLittlePolyclinic.models.Doctor;
import com.polyclinic.MyLittlePolyclinic.models.Image;
import com.polyclinic.MyLittlePolyclinic.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DoctorsService {
    private final DoctorRepository doctorRepository;

    public List<Doctor> listDoctors(String specialization) {
        if (specialization != null) return doctorRepository.findBySpecialization(specialization);
        return doctorRepository.findAll();
    }
    public void addDoctor(Doctor doctor, MultipartFile file) throws IOException {
        Image image;

        if (file.getSize() != 0) {
            image = toImageEntity(file);
            doctor.addImageToDoctor(image);
        }

        log.info("Adding new Doctor. NameSurname: {}; PhoneNumber: {}", doctor.getNameSurname(), doctor.getPhoneNumber());
        doctorRepository.save(doctor);
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void removeDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }
}
