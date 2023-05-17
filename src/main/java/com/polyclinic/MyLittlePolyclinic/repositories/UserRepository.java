package com.polyclinic.MyLittlePolyclinic.repositories;


import com.polyclinic.MyLittlePolyclinic.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByName(String name);
}
