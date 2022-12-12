package com.project.polyclinic.controllers;

import com.project.polyclinic.models.*;
import com.project.polyclinic.repo.*;
import com.project.polyclinic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private DoctorTypeRepo doctorTypeRepo;
    @Autowired
    private DoctorService doctorService;
    @GetMapping("/registration_doctor")
    public String registration(Model model){
        Iterable<Doctor_type> doctor_types = doctorTypeRepo.findAll();
        model.addAttribute("doctor_types", doctor_types);
        return "registration_doctor";
    }

    @PostMapping("/registration_doctor")
    public String addUser(User user,
//                          @RequestParam(required = true) boolean is_doctor,
                          @RequestParam Integer doctor_type,
                          @RequestParam String full_name,
                          @RequestParam String username,
                          @RequestParam String password,
                          Map<String, Object> model){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null){
            model.put("message", "User exists");
            return "registration_doctor";
        }

        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
        user.setIsDoctor(true);
        user.setIsAdmin(false);
        user.setPassword(password);
        user.setUsername(username);
        userRepository.save(user);

        Doctor doctor = new Doctor(
                user.getId(),
                doctor_type,
                full_name);

        doctorRepo.save(doctor);

        return "redirect:/login";
    }

    @GetMapping("/registration_patient")
    public String registration_patient(Model model){
        return "registration_patient";
    }

    @PostMapping("/registration_patient")
    public String addUserPatient(User user,
//                          @RequestParam(required = true) boolean is_doctor,
                          @RequestParam Long polis,
                          @RequestParam String full_name,
                                 @RequestParam String birth_date,
                          @RequestParam String username,
                          @RequestParam String password,
                          Map<String, Object> model){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null){
            model.put("message", "User exists");
            return "registration_patient";
        }

        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
        user.setIsDoctor(false);
        user.setIsAdmin(false);
        user.setPassword(password);
        user.setUsername(username);
        userRepository.save(user);

        Patient patient = new Patient(
                user.getId(),
                polis,
                birth_date,
                full_name);

        patientRepo.save(patient);

        return "redirect:/login";
    }


//    @GetMapping("/addinfo")
//    public String addInfoGet(User user,
//                          Model model){
//
//        Iterable<Doctor_type> doctor_types = doctorTypeRepo.findAll();
//        model.addAttribute("doctor_types", doctor_types);
//
//        return "addInfo";
//    }
//
//    @PostMapping("/addinfo")
//    public String addInfo(User user,
//                          @RequestParam Integer doctor_type,
//                          @RequestParam String full_name,
//                          Model model){
//
//        Doctor doctor = new Doctor(
//                user.getId(),
//                doctor_type,
//                full_name);
//
//        doctorRepo.save(doctor);
//
//        return "redirect:/login";
//    }
}
