package com.project.polyclinic.controllers;
import com.project.polyclinic.models.*;
import com.project.polyclinic.repo.*;
import com.project.polyclinic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private Time_slotService time_slotService;
    @Autowired
    private Time_slotRepo time_slotRepo;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private Doctor_typeService doctorTypeService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentRepo appointmentRepo;

    @GetMapping ("/userpage")
    public String userpage(
            @AuthenticationPrincipal User user,
            Authentication auth,
            Model model) {

        boolean isDoctor = userService.getUserByUserId(userService.getUserId(auth)).getIsDoctor();
        boolean isAdmin = userService.getUserByUserId(userService.getUserId(auth)).getIsAdmin();
        model.addAttribute("isDoctor", isDoctor);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("title", "Личный кабинет");
        int user_id = userService.getUserId(auth);
        model.addAttribute("username", user.getUsername());
//        String auth = user.getAuthorities().toString();
//        model.addAttribute("auth", auth);

        boolean is_doctor = user.getIsDoctor();
        model.addAttribute("is_doctor", is_doctor);

        if (!is_doctor) {
            Patient patient = patientService.getPatientByUserId(user_id);
            model.addAttribute("full_name", patient.getFull_name());
            model.addAttribute("birth_date", patient.getBirth_date());
            model.addAttribute("polis", patient.getPolis());
            model.addAttribute("userId", patient.getUserId());
            model.addAttribute("user_time_slots", appointmentService.getAllAppointmentsByUserId(user_id));
            model.addAttribute("doctorService", doctorService);
            model.addAttribute("doctorTypeService", doctorTypeService);
        }
        else {
            Doctor doctor = doctorService.getDoctorByUserId(user_id);
            model.addAttribute("full_name", doctor.getFull_name());
            model.addAttribute("doctor_type", doctorTypeService.getDoctorTypeById(doctor.getDoctorTypeId()).getDoctor_type());
            model.addAttribute("doctor_time_slots", appointmentService.getAllAppointmentsByDoctorId(doctor.getDoctorId()));
            model.addAttribute("patientService", patientService);
            model.addAttribute("userId", doctor.getUserId());
        }
        return "userpage";
    }

    @PostMapping("/deleteapp/{appointmentId}")
    public String deleteAppointment(@PathVariable(value="appointmentId") Integer appointmentId, Model model){
        appointmentRepo.deleteById(appointmentId);
        return "redirect:/userpage";
    }

    @PostMapping("/changePassword/{id}")
    public String changePassword(@PathVariable(value="id") int id, String password, Model model){
        User user = userService.getUserByUserId(id);
        user.setPassword(password);
        userRepo.save(user);
        return "redirect:/userpage";
    }

}
