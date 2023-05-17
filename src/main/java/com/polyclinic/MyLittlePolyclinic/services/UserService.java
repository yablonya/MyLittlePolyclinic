package com.polyclinic.MyLittlePolyclinic.services;

import com.polyclinic.MyLittlePolyclinic.models.TimeForAdmission;
import com.polyclinic.MyLittlePolyclinic.models.User;
import com.polyclinic.MyLittlePolyclinic.models.enums.Role;
import com.polyclinic.MyLittlePolyclinic.repositories.TimeForAdmissionRepository;
import com.polyclinic.MyLittlePolyclinic.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TimeForAdmissionRepository admissionRepository;

    public boolean createUser (User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.getRoles().add(Role.ROLE_ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public List<User> usersList(String name) {
        if (name != null) return userRepository.findByName(name);
        return userRepository.findAll();
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setActive(false);
            log.info("Ban user with id: {}; email: {}", user.getId(), user.getEmail());
            userRepository.save(user);
        }
    }

    public void unbanUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setActive(true);
            log.info("Unban user with id: {}; email: {}", user.getId(), user.getEmail());
            userRepository.save(user);
        }
    }

    public void changeUserRoles(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            if (user.getRoles().contains(Role.valueOf("ROLE_ADMIN"))) {
                user.getRoles().clear();
                user.getRoles().add(Role.valueOf("ROLE_USER"));
            } else {
                user.getRoles().clear();
                user.getRoles().add(Role.valueOf("ROLE_ADMIN"));
            }
            userRepository.save(user);
        }
    }

    public void addAdmissionToUser(Principal principal, Long id) {
        TimeForAdmission time = admissionRepository.findById(id).orElse(null);
        User user = getUserByPrincipal(principal);

        if (time != null) {
            time.setUser(user);
            user.getAdmissions().add(time);
            userRepository.save(user);
            admissionRepository.save(time);
        }
    }

    public void removeAdmissionFromUser(Principal principal, Long id) {
        TimeForAdmission time = admissionRepository.findById(id).orElse(null);
        User user = getUserByPrincipal(principal);

        if (time != null) {
            time.setUser(null);
            user.getAdmissions().removeIf(hour -> hour.getId().equals(id));
            userRepository.save(user);
            admissionRepository.save(time);
        }
    }
}
