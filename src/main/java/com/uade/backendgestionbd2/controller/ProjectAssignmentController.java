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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/project-assignment")
public class ProjectAssignmentController {

    @Autowired
    private ProjectAssignmentsService projectAssignmentsService;

    @PostMapping("/assign")
    public ResponseEntity<Map<String, String>> assignUserToProject(@RequestBody ProjectAssignmentDto projectAssignment) {
        int userId = projectAssignment.getUserId();
        int projectId = projectAssignment.getProjectId();
        Map<String, String> response = new HashMap<>();
        try {
            projectAssignmentsService.assignmentResourceToProject(projectId, userId);
            response.put("message", "User assigned to project successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteUserFromProject(@RequestBody ProjectAssignmentDto projectAssignment) {
        int userId = projectAssignment.getUserId();
        int projectId = projectAssignment.getProjectId();
        Map<String, String> response = new HashMap<>();
        try {
            projectAssignmentsService.deleteAssignment(projectId, userId);
            response.put("message", "User deleted from project successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
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
