package com.uade.backendgestionbd2.controller;

import com.uade.backendgestionbd2.dto.ProjectAssignmentDto;
import com.uade.backendgestionbd2.dto.UpdateAssignmentDto;
import com.uade.backendgestionbd2.service.ProjectAssignmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project-assignment")
public class ProjectAssignmentController {

    @Autowired
    private ProjectAssignmentsService projectAssignmentsService;

    @PostMapping("/assign")
    public void assignUserToProject(@RequestBody ProjectAssignmentDto projectAssignment) {
        int userId = projectAssignment.getUserId();
        int projectId = projectAssignment.getProjectId();

        projectAssignmentsService.assignmentResourceToProject(projectId, userId);
    }

    @DeleteMapping("/delete")
    public void deleteUserFromProject(@RequestBody ProjectAssignmentDto projectAssignment) {
        int userId = projectAssignment.getUserId();
        int projectId = projectAssignment.getProjectId();
        projectAssignmentsService.deleteAssignment(projectId, userId);
}

    @PutMapping("/update")
    public void updateAssignment(@RequestBody UpdateAssignmentDto projectAssignment) {
        int userId = projectAssignment.getUserId();
        int projectId = projectAssignment.getProjectId();
        int newUserId = projectAssignment.getNewUserId();

        projectAssignmentsService.modifyUserAssignment(projectId, userId, newUserId);
    }

}
