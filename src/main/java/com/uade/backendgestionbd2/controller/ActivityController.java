package com.uade.backendgestionbd2.controller;



import com.uade.backendgestionbd2.model.Activities;
import com.uade.backendgestionbd2.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
@AllArgsConstructor
public class ActivityController {

    @Autowired
    private final ActivityService activityService;

    @PostMapping("/add")
    public void addActivity(@RequestBody Activities activity) {
        activityService.addActivity(activity);
    }



}
