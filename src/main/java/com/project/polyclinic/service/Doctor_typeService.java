package com.project.polyclinic.service;

import com.project.polyclinic.models.Doctor;
import com.project.polyclinic.models.Doctor_type;
import com.project.polyclinic.repo.DoctorRepo;
import com.project.polyclinic.repo.DoctorTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Doctor_typeService {

    private DoctorTypeRepo doctorTypeRepo;

    @Autowired
    public Doctor_typeService(DoctorTypeRepo doctorTypeRepo){
        this.doctorTypeRepo = doctorTypeRepo;
    }


    public Doctor_type getDoctorTypeById(int doctorTypeId) {return doctorTypeRepo.findByDoctorTypeId(doctorTypeId);}

}
