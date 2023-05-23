package com.polyclinic.MyLittlePolyclinic.repositories;

import com.polyclinic.MyLittlePolyclinic.models.TimeForAdmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeForAdmissionRepository extends JpaRepository<TimeForAdmission, Long> {
}
