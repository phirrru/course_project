package com.project.polyclinic.controllers;

import com.project.polyclinic.models.*;
import com.project.polyclinic.repo.*;
import com.project.polyclinic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private DoctorTypeRepo doctorTypeRepo;


    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;


    @Autowired
    private Time_slotRepo time_slotRepo;

    @Autowired
    private Time_slotService time_slotService;

    @GetMapping("/")
    public String mainPage(User user,
            Authentication auth,
            Model model) {
        model.addAttribute("title", "Запись к врачу");

        boolean is_doctor = userService.getUserByUserId(userService.getUserId(auth)).getIsDoctor();
        boolean isDoctor = userService.getUserByUserId(userService.getUserId(auth)).getIsDoctor();
        boolean isAdmin = userService.getUserByUserId(userService.getUserId(auth)).getIsAdmin();
        model.addAttribute("isDoctor", isDoctor);
        model.addAttribute("isAdmin", isAdmin);
//        model.addAttribute("user_id", userService.getUserId(auth));
        model.addAttribute("is_doctor", is_doctor);
        Iterable<Doctor_type> doctor_types = doctorTypeRepo.findAll();
        model.addAttribute("doctor_types", doctor_types);

        return "index";
    }

    @Controller
    public class DoctorTypeController {

        @GetMapping("/doctor_type/{doctor_type_id}")
        public String doctorTypePage (@PathVariable(value="doctor_type_id") Integer doctor_type_id, Model model, Authentication auth) {
            boolean isDoctor = userService.getUserByUserId(userService.getUserId(auth)).getIsDoctor();
            boolean isAdmin = userService.getUserByUserId(userService.getUserId(auth)).getIsAdmin();
            model.addAttribute("isDoctor", isDoctor);
            model.addAttribute("isAdmin", isAdmin);
            model.addAttribute("doctor_type", "doctor_type");
            Optional<Doctor_type> doctor_types = doctorTypeRepo.findById(doctor_type_id);
            ArrayList<Doctor_type> res1 = new ArrayList<>();
            doctor_types.ifPresent(res1::add);
            model.addAttribute("doctor_types", res1);

            model.addAttribute("doctor_type_id", doctor_type_id);

            model.addAttribute("doctors", doctorService.getAllDoctorsByDoctorTypeId(doctor_type_id));
            List<Doctor> doctors = doctorService.getAllDoctorsByDoctorTypeId(doctor_type_id);


            model.addAttribute("doctors_size", doctors.size());
            model.addAttribute("time_slotRepo", time_slotRepo);
            model.addAttribute("time_slot", time_slotRepo);

            List<Time_slot> time_slots = new ArrayList<>();
            List<Time_slot> new_time_slots = new ArrayList<>();
            for (Doctor doctor : doctors) {
                new_time_slots = time_slotService.getAllTimeslotsByDoctorId(doctor.getDoctorId());
                time_slots.addAll(new_time_slots);
            }
                model.addAttribute("time_slots", time_slots);

            return "doctor-type-page";
        }


        @PostMapping("/generate_timeslots")
        public String generateTimeslots(
                @AuthenticationPrincipal User user,
                @RequestParam(required = true) Integer weekday,
                @RequestParam(required = true) Integer interval,
                @RequestParam(required = true) Integer start_hour,
                @RequestParam(required = true) Integer end_hour,
                Authentication authentication,
                Model model
        ) {

            for (int i = start_hour; i < end_hour; ++i ) {
                Integer hours = i;
                Integer minutes = 0;
                String time;
                for (int j = 0; j < interval; ++j) {
                    time = hours.toString() + ':' + minutes.toString();
                    if (minutes == 0) time = time + '0';
                    minutes = minutes + 60/interval;
                    if (time_slotService.getTimeSlotByTimeDoctorIdDay(time, doctorService.getDoctorByUserId(user.getId()).getDoctorId(), weekday) == null) {
                        Time_slot newTimeslot = new Time_slot();
                        newTimeslot.setTime(time);
                        newTimeslot.setDoctorId(doctorService.getDoctorByUserId(user.getId()).getDoctorId());
                        newTimeslot.setDay(weekday);
                        time_slotService.saveTimeslot(newTimeslot);
                    }
//                    System.out.println(time);
                }
            }



            return "redirect:/";

        }


        @PostMapping("/doctor/{id}")
        public String addAppointment(
                @AuthenticationPrincipal User user,
                @RequestParam(required = true) Integer picked_time_slot_id,
                Authentication authentication,
                @PathVariable(value = "id") int doctorId,
                Model model
        ) {
            int userId = userService.getUserId(authentication);

            Time_slot picked_time_slot = time_slotRepo.findByTimeSlotId(picked_time_slot_id);
            Appointment newApp = new Appointment();
            newApp.setDoctorId(picked_time_slot.getDoctorId());
            newApp.setDay(picked_time_slot.getDay());
            newApp.setTime(picked_time_slot.getTime());
            newApp.setUserId(userService.getUserId(authentication));
            appointmentService.saveAppointment(newApp);
            time_slotService.deleteTimeslotById(picked_time_slot_id);



            return "redirect:/";

            }
        }
    }




