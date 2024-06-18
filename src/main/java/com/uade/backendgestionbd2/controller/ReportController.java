package com.uade.backendgestionbd2.controller;

import com.uade.backendgestionbd2.model.Comments;
import com.uade.backendgestionbd2.model.Projects;
import com.uade.backendgestionbd2.model.Tasks;
import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;
    @Autowired
    private FilesService filesService;

    @GetMapping("/pdf/{projectId}")
    public ResponseEntity<byte[]> getProjectReport(@PathVariable int projectId) {
        Projects project = projectService.getProjectById(projectId);
        List<Tasks> tasks = taskService.getAllTasksByProject(projectId);
        List<Users> users = userService.findUsersByProjectId(projectId);
        List<Comments> comments = commentService.getCommentsByProject(projectId);

        byte[] pdfContent = filesService.generateProjectReportPDF(project, tasks, users, comments);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=project_report.pdf");

        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }


    @GetMapping("/excel/{projectId}")
    public ResponseEntity<byte[]> getProjectReportExcel(@PathVariable int projectId) {
        Projects project = projectService.getProjectById(projectId);
        List<Tasks> tasks = taskService.getAllTasksByProject(projectId);
        List<Users> users = userService.findUsersByProjectId(projectId);
        List<Comments> comments = commentService.getCommentsByProject(projectId);

        byte[] excelContent = filesService.generateProjectReportExcel(project, tasks, users, comments);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=project_report.xlsx");

        return new ResponseEntity<>(excelContent, headers, HttpStatus.OK);
    }
}

