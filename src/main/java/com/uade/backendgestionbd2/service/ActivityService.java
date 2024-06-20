package com.uade.backendgestionbd2.service;


import com.uade.backendgestionbd2.model.Activities;
import com.uade.backendgestionbd2.model.Tasks;
import com.uade.backendgestionbd2.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private TaskService taskService;

    public void addActivity(Activities activity) {
       activityRepository.save(activity);
        int progress = activity.getProgress_percentage();
        int taskId = activity.getTask_id();
        Tasks task = taskService.getTaskById(taskId);
        task.setStatus(progress);
        taskService.updateTask(task);
    }

    public void updateActivity(Activities activity) {
        activityRepository.save(activity);
        int progress = activity.getProgress_percentage();
        int taskId = activity.getTask_id();
        Tasks task = taskService.getTaskById(taskId);
        task.setStatus(progress);
        taskService.updateTask(task);
    }

    public void deleteActivity(String activityId) {
        Activities activity = activityRepository.findByActivityId(activityId);
        activityRepository.delete(activity);
        List<Activities> activitiesList = activityRepository.findByTaskId(activity.getTask_id());
        if (activitiesList.isEmpty()) {
            Tasks task = taskService.getTaskById(activity.getTask_id());
            task.setStatus(0);
            taskService.updateTask(task);
        }else{
            Activities lastActivity = activitiesList.getLast();
            Tasks task = taskService.getTaskById(lastActivity.getTask_id());
            task.setStatus(lastActivity.getProgress_percentage());
            taskService.updateTask(task);
        }
    }

    public Activities getActivityById(String activityId) {
        return activityRepository.findByActivityId(activityId);
    }

    public List<Activities> getActivitiesByTaskId(int taskId) {
        return activityRepository.findByTaskId(taskId);
    }
}
