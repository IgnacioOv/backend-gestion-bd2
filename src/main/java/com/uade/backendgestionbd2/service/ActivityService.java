package com.uade.backendgestionbd2.service;


import com.uade.backendgestionbd2.dto.ActivitiesRequest;
import com.uade.backendgestionbd2.model.Activities;
import com.uade.backendgestionbd2.model.Tasks;
import com.uade.backendgestionbd2.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private TaskService taskService;

    public void addActivity(ActivitiesRequest activity) {
        int taskId = activity.getTask_id();
        int userId = Integer.parseInt(activity.getUser_id()); // Convertir String a int si userId es un String
        if (taskService.getTaskById(taskId).getUser().getUser_id() != userId) {
            throw new RuntimeException("User is not assigned to this task");
        }
        Activities newActivity = new Activities();
        newActivity.setTask_id(taskId);
        newActivity.setUser_id(activity.getUser_id());
        newActivity.setProgress_percentage(activity.getProgress_percentage());
        newActivity.setTimestamp(activity.getTimestamp());
        newActivity.setDescription(activity.getDescription());
        newActivity.setTime_worked(activity.getTime_worked());
        activityRepository.save(newActivity);
        int progress = activity.getProgress_percentage();
        Tasks task = taskService.getTaskById(taskId);
        task.setStatus(progress);
        taskService.updateTask(task);
    }

    public void updateActivity(Activities activity) {
        int taskId = activity.getTask_id();
        int userId = Integer.parseInt(activity.getUser_id()); // Convertir String a int si userId es un String
        if (taskService.getTaskById(taskId).getUser().getUser_id() != userId) {
            throw new RuntimeException("User is not assigned to this task");
        }
        activityRepository.save(activity);
        int progress = activity.getProgress_percentage();
        Tasks task = taskService.getTaskById(taskId);
        task.setStatus(progress);
        taskService.updateTask(task);
    }

    public void deleteActivity(String activityId) {
        Activities activity = activityRepository.findByActivityId(activityId);
        if (activity == null) {
            throw new RuntimeException("Activity not found");
        }
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
        List<Activities> activities = activityRepository.findByTaskId(taskId);

        // Ordenar actividades por timestamp de forma descendente (más nuevo primero)
        activities.sort(Comparator.comparing(Activities :: getTimestamp).reversed());

        return activities;
    }

    public ResponseEntity<Object> deleteLastActivityFromTask(int taskId){
        List<Activities> activities = activityRepository.findByTaskId(taskId);
        if (activities.isEmpty()) {
            return ResponseEntity.badRequest().body("No activities found for task with ID " + taskId);
        }
        Activities lastActivity = activities.getLast();
        activityRepository.delete(lastActivity);
        activities.remove(lastActivity);
        if (activities.isEmpty()) {
            Tasks task = taskService.getTaskById(taskId);
            task.setStatus(0);
            taskService.updateTask(task);
        }else{
            Activities newLastActivity = activities.getLast();
            Tasks task = taskService.getTaskById(newLastActivity.getTask_id());
            task.setStatus(newLastActivity.getProgress_percentage());
            taskService.updateTask(task);
        }
        return ResponseEntity.ok("Activity deleted successfully");

    }

}
