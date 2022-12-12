package com.project.polyclinic.models;


import javax.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;

    @Column(name = "user_id", nullable = true)
    private Integer userId;

    @Column(name = "doctor_type_id", nullable = false)
    private int doctorTypeId;

    @Column(name = "full_name", nullable = false)
    private String full_name;

    public Doctor(){
        this.userId = 0;
    }

    public Doctor(int userId, int doctorTypeId, String full_name) {
        this.userId = userId;
        this.doctorTypeId = doctorTypeId;
        this.full_name = full_name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getDoctorTypeId() {
        return doctorTypeId;
    }

    public void setDoctorTypeId(Integer doctorTypeId) {
        this.doctorTypeId = doctorTypeId;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
