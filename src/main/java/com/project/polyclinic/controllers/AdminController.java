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

//th:action="@{/admin/{user_id}(user_id=user.username)">
@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepo patientRepo;
    @GetMapping ("/admin_panel")
    public String adminPanel(
            @AuthenticationPrincipal User user,
            Authentication auth,
            Model model) {

        boolean isDoctor = userService.getUserByUserId(userService.getUserId(auth)).getIsDoctor();
        boolean isAdmin = userService.getUserByUserId(userService.getUserId(auth)).getIsAdmin();
        model.addAttribute("isDoctor", isDoctor);
        model.addAttribute("isAdmin", isAdmin);
//        String auth = user.getAuthorities().toString();
//        model.addAttribute("auth", auth);
        boolean is_admin = user.getIsAdmin();
        model.addAttribute("is_admin", is_admin);
        model.addAttribute("user", userService.getAllUsers());
        return "admin";
    }



    @PostMapping("/admin_panel/{user_id}")
    public String updateRole(
            @AuthenticationPrincipal User user,
            @RequestParam(required = true) String new_role,
            Authentication authentication,
            @PathVariable(value = "user_id") int userId,
            Model model
    ) {
        User targetUser = userService.getUserByUserId(userId);
        boolean new_role_bool = Boolean.parseBoolean(new_role);
        if (new_role_bool) targetUser.setIsAdmin(true);
        else
            targetUser.setIsAdmin(false);
        userRepo.save(targetUser);

        return "redirect:/admin_panel";

    }
    @PostMapping("/admin_panel/delete/{user_id}")
    public String deleteUser(
            @AuthenticationPrincipal User user,
            Authentication authentication,
            @PathVariable(value = "user_id") int userId,
            Model model
    ) {
        User targetUser = userService.getUserByUserId(userId);
        if (targetUser.getIsDoctor()) {
            doctorRepo.delete(doctorService.getDoctorByUserId(userId));
        }
        else {
            patientRepo.delete(patientService.getPatientByUserId(userId));
        }
        userRepo.delete(targetUser);

        return "redirect:/admin_panel";

    }
}
