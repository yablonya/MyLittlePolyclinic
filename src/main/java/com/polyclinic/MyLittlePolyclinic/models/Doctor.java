package com.polyclinic.MyLittlePolyclinic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "nameSurname")
    private String nameSurname;
    @Column(name = "specialization")
    private String specialization;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "doctor")
    private List<TimeForAdmission> schedule = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Image photo;
    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }
    public void addImageToDoctor(Image image) {
        image.setDoctor(this);
        photo = image;
    }
    public void addSchedule() {
        int start = 8;

        for(int i = start; i <= 17; i++) {
            TimeForAdmission time = new TimeForAdmission();
            time.setTime(i + ":00 - " + (i + 1) + ":00");
            time.setDoctor(this);
            schedule.add(time);
        }
    }
}
