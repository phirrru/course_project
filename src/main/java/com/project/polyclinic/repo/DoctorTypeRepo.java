package com.project.polyclinic.repo;


import com.project.polyclinic.models.Doctor_type;
import org.springframework.data.repository.CrudRepository;

public interface DoctorTypeRepo extends CrudRepository<Doctor_type, Integer> {
    Doctor_type findByDoctorTypeId (int doctorTypeId);
}
