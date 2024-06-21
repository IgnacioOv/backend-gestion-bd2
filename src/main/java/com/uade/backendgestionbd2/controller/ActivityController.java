package com.uade.backendgestionbd2.controller;



import com.uade.backendgestionbd2.model.Activities;
import com.uade.backendgestionbd2.service.ActivityService;
import com.uade.backendgestionbd2.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@AllArgsConstructor
public class ActivityController {

    @Autowired
    private final ActivityService activityService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public void addActivity(@RequestBody Activities activity) {
        activityService.addActivity(activity);
    }

    @GetMapping("task/{taskId}")
    public List<Activities> getActivitiesByTaskId(@PathVariable int taskId) {
        return activityService.getActivitiesByTaskId(taskId);
    }

    @PostMapping("/update")
    public void updateActivity(@RequestBody Activities activity) {
        activityService.updateActivity(activity);
    }

    @DeleteMapping("/delete/{activityId}")
    public void deleteActivity(@PathVariable String activityId) {
        activityService.deleteActivity(activityId);

    }

    @GetMapping("/get/{activityId}")
    public Activities getActivityById(@PathVariable String activityId) {
        return activityService.getActivityById(activityId);
    }



}
