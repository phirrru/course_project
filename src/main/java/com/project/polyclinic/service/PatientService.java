package com.project.polyclinic.service;

import com.project.polyclinic.models.Patient;
import com.project.polyclinic.repo.AppointmentRepo;
import com.project.polyclinic.repo.PatientRepo;
import com.project.polyclinic.repo.Time_slotRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private PatientRepo patientRepo;

    @Autowired
    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public Patient getPatientByUserId (int userId) {
        return patientRepo.findByUserId(userId);
    }
}
