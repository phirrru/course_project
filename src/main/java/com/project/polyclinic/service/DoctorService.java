package com.project.polyclinic.service;

import com.project.polyclinic.models.Doctor;
import com.project.polyclinic.repo.DoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private DoctorRepo doctorRepo;

    @Autowired
    public DoctorService(DoctorRepo doctorRepo){
        this.doctorRepo = doctorRepo;
    }



    public List<Doctor> getAllDoctorsByDoctorTypeId(int doctorTypeId) {
        return doctorRepo.findAllByDoctorTypeId(doctorTypeId);
    }

    public Doctor getDoctorByDoctorId(int doctorId) {
        return doctorRepo.findByDoctorId(doctorId);
    }
    public Doctor getDoctorByUserId(int userId) {
        return doctorRepo.findByUserId(userId);
    }

}

