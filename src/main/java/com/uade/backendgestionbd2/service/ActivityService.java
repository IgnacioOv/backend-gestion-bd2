package com.uade.backendgestionbd2.service;


import com.uade.backendgestionbd2.model.Activities;
import com.uade.backendgestionbd2.repository.ActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private ActivityRepository activityRepository;

    public void addActivity(Activities activity) {
       activityRepository.save(activity);
    }

    public void updateActivity(Activities activity) {
        activityRepository.save(activity);
    }

    public void deleteActivity(Long activityId) {
        activityRepository.deleteById(activityId);
    }

    public void getActivityById(Long activityId) {
        activityRepository.findById(activityId);
    }

}
