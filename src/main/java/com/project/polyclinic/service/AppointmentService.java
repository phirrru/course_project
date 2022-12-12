package com.project.polyclinic.service;

import com.project.polyclinic.models.Appointment;
import com.project.polyclinic.repo.AppointmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    @Autowired
    public AppointmentService(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }
    private AppointmentRepo appointmentRepo;
    public void saveAppointment(Appointment app) {
        appointmentRepo.save(app);
    }
    public List<Appointment> getAllAppointmentsByUserId(int userId) {
        return appointmentRepo.findAllByUserId(userId);
    }
    public List<Appointment> getAllAppointmentsByDoctorId(int doctorId) {
        return appointmentRepo.findAllByDoctorId(doctorId);
    }
    public Appointment getAppointmentById (int appId) {
        return appointmentRepo.findByAppointmentId(appId);
    }
}
