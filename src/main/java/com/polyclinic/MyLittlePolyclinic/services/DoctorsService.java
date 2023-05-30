package com.polyclinic.MyLittlePolyclinic.services;

import com.polyclinic.MyLittlePolyclinic.models.Doctor;
import com.polyclinic.MyLittlePolyclinic.models.Image;
import com.polyclinic.MyLittlePolyclinic.models.TimeForAdmission;
import com.polyclinic.MyLittlePolyclinic.repositories.DoctorRepository;
import com.polyclinic.MyLittlePolyclinic.repositories.TimeForAdmissionRepository;
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
    private final TimeForAdmissionRepository admissionRepository;

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
        doctor.addSchedule();

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

    public List<Doctor> doctorList() {
        return doctorRepository.findAll();
    }

    public void addTimeForAdmission(Long id, String hour) {
        Doctor doctor = getDoctorById(id);
        if (doctor != null && hour != null) {
            TimeForAdmission time = new TimeForAdmission();
            time.setTime(hour);
            time.setDoctor(doctor);
            doctor.getSchedule().add(time);
            admissionRepository.save(time);
            doctorRepository.save(doctor);
        }
    }

    public void removeTimeForAdmission(Long admission_id) {
        TimeForAdmission time = admissionRepository.findById(admission_id).orElse(null);

        if (time != null) {
            admissionRepository.deleteById(admission_id);
            log.info("Delete admission. Id: {}", admission_id);
        }
    }

    public List<TimeForAdmission> sortOutputSchedule(List<TimeForAdmission> schedule) {
        return schedule.stream()
                .sorted(((o1, o2) -> prepareTimeToSort(o1).compareTo(prepareTimeToSort(o2))))
                .toList();
    }

    public Integer prepareTimeToSort(TimeForAdmission time) {
        return Integer.parseInt(time.getTime().split(":")[0]);
    }
}
