package com.project.polyclinic.models;


import javax.persistence.*;

@Entity
@Table(name = "doctor_type")
public class Doctor_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorTypeId;

    @Column(name = "doctor_type", nullable = false)
    private String doctor_type ;

    public int getDoctorTypeId() {
        return doctorTypeId;
    }

    public void setDoctorTypeId(int doctorTypeId) {
        this.doctorTypeId = doctorTypeId;
    }

    public String getDoctor_type() {
        return doctor_type;
    }

    public void setDoctor_type(String doctor_type) {
        this.doctor_type = doctor_type;
    }
}
