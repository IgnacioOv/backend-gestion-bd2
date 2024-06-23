package com.uade.backendgestionbd2.controller;

import com.uade.backendgestionbd2.dto.ProjectAssignmentDto;
import com.uade.backendgestionbd2.dto.UpdateAssignmentDto;
import com.uade.backendgestionbd2.service.ProjectAssignmentsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project-assignment")
public class ProjectAssignmentController {

    @Autowired
    private ProjectAssignmentsService projectAssignmentsService;

    @PostMapping("/assign")
    public ResponseEntity<Object> assignUserToProject(@RequestBody ProjectAssignmentDto projectAssignment) {
        int userId = projectAssignment.getUserId();
        int projectId = projectAssignment.getProjectId();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectAssignmentsService.assignmentResourceToProject(projectId, userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUserFromProject(@RequestBody ProjectAssignmentDto projectAssignment) {
        int userId = projectAssignment.getUserId();
        int projectId = projectAssignment.getProjectId();

        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectAssignmentsService.deleteAssignment(projectId, userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }
}

    @PutMapping("/update")
    public ResponseEntity<Object> updateAssignment(@RequestBody UpdateAssignmentDto projectAssignment) {
        int userId = projectAssignment.getUserId();
        int projectId = projectAssignment.getProjectId();
        int newUserId = projectAssignment.getNewUserId();

        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectAssignmentsService.modifyUserAssignment(projectId, userId, newUserId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }
    }

}
