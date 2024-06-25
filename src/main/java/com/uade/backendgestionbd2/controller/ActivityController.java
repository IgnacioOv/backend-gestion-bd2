package com.uade.backendgestionbd2.controller;



import com.uade.backendgestionbd2.dto.ActivitiesRequest;
import com.uade.backendgestionbd2.model.Activities;
import com.uade.backendgestionbd2.service.ActivityService;
import com.uade.backendgestionbd2.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activity")
@AllArgsConstructor
public class ActivityController {

    @Autowired
    private final ActivityService activityService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addActivity(@RequestBody ActivitiesRequest activity) {
        Map<String, String> response = new HashMap<>();
        try {
            int taskId = activity.getTask_id();
            int userId = Integer.parseInt(activity.getUser_id()); // Convertir String a int si userId es un String
            if (taskService.getTaskById(taskId).getUser().getUser_id() != userId) {
                throw new RuntimeException("User is not assigned to this task");
            }
            activityService.addActivity(activity);
            // Lógica para agregar la actividad (no se muestra aquí)
            response.put("message", "Activity added successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("task/{taskId}")
    public List<Activities> getActivitiesByTaskId(@PathVariable int taskId) {
        return activityService.getActivitiesByTaskId(taskId);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateActivity(@RequestBody Activities activity) {
        try {
            activityService.updateActivity(activity);
            // Crear un HashMap para la respuesta de éxito
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Activity updated successfully");
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            // En caso de error, devolver un HashMap con el mensaje de error
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{activityId}")
    public ResponseEntity<Object> deleteActivity(@PathVariable String activityId) {
        try{
            activityService.deleteActivity(activityId);
            return ResponseEntity.ok("Activity deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }


    }

    @GetMapping("/get/{activityId}")
    public Activities getActivityById(@PathVariable String activityId) {
        return activityService.getActivityById(activityId);
    }

    @DeleteMapping("/delete/task/{taskId}/{userId}")
    public ResponseEntity<Object> deleteLastActivity(@PathVariable int taskId, @PathVariable int userId) {
        try {
            activityService.deleteLastActivityFromTask(taskId, userId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Activity deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // En caso de error, devolver un HashMap con el mensaje de error
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }



}
