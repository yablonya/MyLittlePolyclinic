package com.polyclinic.MyLittlePolyclinic.repositories;

import com.polyclinic.MyLittlePolyclinic.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
