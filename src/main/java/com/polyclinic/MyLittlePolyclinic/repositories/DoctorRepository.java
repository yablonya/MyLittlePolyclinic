package com.polyclinic.MyLittlePolyclinic.repositories;

import com.polyclinic.MyLittlePolyclinic.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialization(String specialization);
}
