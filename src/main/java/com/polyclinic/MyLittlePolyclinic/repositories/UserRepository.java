package com.polyclinic.MyLittlePolyclinic.repositories;

import com.polyclinic.MyLittlePolyclinic.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
