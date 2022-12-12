package com.project.polyclinic.repo;

import com.project.polyclinic.models.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepo extends CrudRepository<Doctor, Integer> {

//    Iterable<Doctor> findAllByDoctorTypeId(int doctorTypeId);
    List<Doctor> findAllByDoctorTypeId(int doctorTypeId);
    Doctor findByDoctorId (int doctorId);
    Doctor findByUserId (int userId);
}
