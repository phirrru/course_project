package com.project.polyclinic.models;

import javax.persistence.*;

@Entity
@Table(name = "time_slot")
public class Time_slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timeSlotId;

    @Column(name = "day", nullable = false)
    private Integer day;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "doctorId", nullable = false)
    private int doctorId;

    public Time_slot() {
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
