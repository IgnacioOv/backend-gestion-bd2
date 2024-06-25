package com.uade.backendgestionbd2.service;


import com.uade.backendgestionbd2.dto.ActivitiesRequest;
import com.uade.backendgestionbd2.model.Activities;
import com.uade.backendgestionbd2.model.Tasks;
import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private TaskService taskService;

    public void addActivity(ActivitiesRequest activity) {
        int taskId = activity.getTask_id();
        int userId = Integer.parseInt(activity.getUser_id());

        // Verificar si el usuario está asignado a esta tarea
        Tasks task = taskService.getTaskById(taskId);
        Users user = taskService.getTaskById(taskId).getUser();
        if(user == null){
            throw new RuntimeException("task not assigned user");
        }
        if (taskService.getTaskById(taskId).getUser().getUser_id() != userId) {
            throw new RuntimeException("User is not assigned to this task");
        }

        // Crear la nueva actividad
        Activities newActivity = new Activities();
        newActivity.setTask_id(taskId);
        newActivity.setUser_id(activity.getUser_id());
        newActivity.setProgress_percentage(activity.getProgress_percentage());
        newActivity.setTimestamp(new Date());  // Establecer el timestamp actual
        newActivity.setDescription(activity.getDescription());
        newActivity.setTime_worked(activity.getTime_worked());

        // Guardar la actividad en la base de datos
        activityRepository.save(newActivity);

        // Actualizar el estado de la tarea
        int progress = activity.getProgress_percentage();
        task.setStatus(progress);
        taskService.updateTask(task);
    }

    public void updateActivity(Activities activity) {
        int taskId = activity.getTask_id();
        int userId = Integer.parseInt(activity.getUser_id()); // Convertir String a int si userId es un String
        Users user = taskService.getTaskById(taskId).getUser();
        if(user == null){
            throw new RuntimeException("task not assigned user");
        }
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

        if (activities != null) {
            // Filtrar actividades que tienen timestamp null
            activities = activities.stream()
                    .filter(activity -> activity.getTimestamp() != null)
                    .collect(Collectors.toList());
            // Ordenar actividades por timestamp de forma descendente (más nuevo primero)
            activities.sort(Comparator.comparing(Activities::getTimestamp).reversed());

        } else {
            // Si la lista es null, devolver una lista vacía en lugar de null
            activities = new ArrayList<>();
        }

        return activities;
    }

    public String deleteLastActivityFromTask(int taskId, int userId) {
        List<Activities> activities = activityRepository.findByTaskId(taskId);

        Activities lastActivity = activities.getLast();
        int userIdAssign = Integer.parseInt(lastActivity.getUser_id());
        if (userIdAssign != userId) {
            throw new RuntimeException("User is not assigned to this task");
        }
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
        return "Activity deleted successfully";

    }

}
