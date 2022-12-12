package com.project.polyclinic.service;

import com.project.polyclinic.models.Appointment;
import com.project.polyclinic.models.Doctor;
import com.project.polyclinic.models.Time_slot;
import com.project.polyclinic.repo.DoctorRepo;
import com.project.polyclinic.repo.Time_slotRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Time_slotService {

    private Time_slotRepo time_slotRepo;

    @Autowired
    public Time_slotService(Time_slotRepo time_slotRepo){
        this.time_slotRepo = time_slotRepo;
    }



    public List<Time_slot> getAllTimeslotsByDoctorId(int doctorId) {
        return time_slotRepo.findAllByDoctorId(doctorId);
    }


    public Time_slot getTimeSlotByTime(String time) {
        return time_slotRepo.findByTime(time);
    }

    @Transactional
    public void deleteTimeslotById(int id) {
        time_slotRepo.deleteByTimeSlotId(id);
    }
    public void saveTimeslot(Time_slot time_slot) {
        time_slotRepo.save(time_slot);
    }

    public Time_slot getTimeSlotByTimeDoctorIdDay (String time, int doctorId, Integer day) {
        return time_slotRepo.findByTimeAndDoctorIdAndDay(time, doctorId, day);
    }
}
