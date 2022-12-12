package com.project.polyclinic.repo;

import com.project.polyclinic.models.Appointment;
import com.project.polyclinic.models.Doctor_type;
import com.project.polyclinic.models.Time_slot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepo extends CrudRepository<Appointment, Integer> {
    List<Appointment> findAllByUserId(int userId);
    List<Appointment> findAllByDoctorId(int doctorId);
    void deleteByAppointmentId(int appointmentId);
    Appointment findByAppointmentId(int appointmentId);
}
