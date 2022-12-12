package com.project.polyclinic.repo;

import com.project.polyclinic.models.Doctor_type;
import com.project.polyclinic.models.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepo extends CrudRepository<Patient, Integer> {
    Patient findByUserId (int userId);
}
