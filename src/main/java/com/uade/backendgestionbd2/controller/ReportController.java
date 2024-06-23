package com.uade.backendgestionbd2.controller;

import com.uade.backendgestionbd2.dto.UserRequestDto;
import com.uade.backendgestionbd2.model.Comments;
import com.uade.backendgestionbd2.model.Projects;
import com.uade.backendgestionbd2.model.Tasks;
import com.uade.backendgestionbd2.model.Users;
import com.uade.backendgestionbd2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        List<UserRequestDto> users = userService.findUsersByProjectId(projectId);
        List<Comments> comments = commentService.getCommentsByProject(projectId);

        byte[] pdfContent = filesService.generateProjectReportPDF(project, tasks, users, comments);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "documento.pdf");
        headers.setContentLength(pdfContent.length);
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }


    @GetMapping("/excel/{projectId}")
    public ResponseEntity<byte[]> getProjectReportExcel(@PathVariable int projectId) {
        Projects project = projectService.getProjectById(projectId);
        List<Tasks> tasks = taskService.getAllTasksByProject(projectId);
        List<UserRequestDto> users = userService.findUsersByProjectId(projectId);
        List<Comments> comments = commentService.getCommentsByProject(projectId);

        byte[] excelContent = filesService.generateProjectReportExcel(project, tasks, users, comments);

        // Configurar HttpHeaders para la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=project_report.xlsx");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Retornar ResponseEntity con el contenido del archivo Excel y los encabezados configurados
        return new ResponseEntity<>(excelContent, headers, HttpStatus.OK);
    }
}

