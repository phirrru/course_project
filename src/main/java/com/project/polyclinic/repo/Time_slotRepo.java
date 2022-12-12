package com.project.polyclinic.repo;


import com.project.polyclinic.models.Doctor;
import com.project.polyclinic.models.Time_slot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Time_slotRepo extends CrudRepository<Time_slot, Integer> {

    List<Time_slot> findAllByDoctorId(int doctorId);
    Time_slot findByTime (String time);
    Time_slot findByTimeSlotId (int timeSlotId);
    int deleteByTimeSlotId(int timeSlotId);
    Time_slot findByTimeAndDoctorIdAndDay (String time, int doctorId, Integer day);
}
