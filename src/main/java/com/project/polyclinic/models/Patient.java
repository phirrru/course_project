package com.project.polyclinic.models;

import javax.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;



    @Column(name = "polis", nullable = true)
    private Long polis;

    @Column(name = "birth_date", nullable = false)
    private String birth_date;

    @Column(name = "full_name", nullable = false)
    private String full_name;


    public Patient() {
    }

    public Patient(Integer userId, Long polis, String birth_date, String full_name) {
        this.userId = userId;
        this.polis = polis;
        this.birth_date = birth_date;
        this.full_name = full_name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Long getPolis() {
        return polis;
    }

    public void setPolis(Long polis) {
        this.polis = polis;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
