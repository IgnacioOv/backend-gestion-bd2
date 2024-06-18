package com.uade.backendgestionbd2.controller;

import com.uade.backendgestionbd2.dto.TaskRequest;
import com.uade.backendgestionbd2.exception.ProjectException;
import com.uade.backendgestionbd2.exception.TaskException;
import com.uade.backendgestionbd2.exception.UserException;
import com.uade.backendgestionbd2.model.Tasks;
import com.uade.backendgestionbd2.service.ProjectService;
import com.uade.backendgestionbd2.service.TaskService;
import com.uade.backendgestionbd2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;


    // add task
    @PostMapping("/")
    public ResponseEntity<Object> createTask(@RequestBody TaskRequest taskRequest) { // sin task id
        try {
            Tasks task = new Tasks();
            task.setName(taskRequest.getName());
            task.setDescription(taskRequest.getDescription());
            task.setProject(projectService.getProjectById(taskRequest.getProject()));
            task.setSkillLevel(taskRequest.getSkillLevel());
            task.setEnd_date(taskRequest.getEndDate());
            task.setStart_date(taskRequest.getStartDate());
            task.setStatus(taskRequest.getStatus());
            task.setUser(userService.getUserById(taskRequest.getUser()));

            // si todo esta bien, crear la tarea
            taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(task);
        } catch (ProjectException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (TaskException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // update task
    @PutMapping("/")
    public ResponseEntity<Object> updateTask(@RequestBody TaskRequest taskRequest) { // si o si con task id
        System.out.println(taskRequest.toString());
        try {
            Tasks task = new Tasks();
            task.setTask_id(taskRequest.getId());
            task.setName(taskRequest.getName());
            task.setUser(userService.getUserById(taskRequest.getUser()));
            task.setDescription(taskRequest.getDescription());
            task.setProject(projectService.getProjectById(taskRequest.getProject()));
            task.setSkillLevel(taskRequest.getSkillLevel());
            task.setEnd_date(taskRequest.getEndDate());
            task.setStart_date(taskRequest.getStartDate());
            task.setStatus(taskRequest.getStatus());

            // si todo esta bien, actualizar la tarea
            taskService.updateTask(task);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (ProjectException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (TaskException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // delete task

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable int taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.status(HttpStatus.OK).body("Task deleted");
        } catch (TaskException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // get task by id
    @GetMapping("/{taskId}")
    public ResponseEntity<Object> getTaskById(@PathVariable int taskId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(taskId));
        } catch (TaskException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // get all tasks by project
    @GetMapping("/project/{projectId}")
    public ResponseEntity<Object> getAllTasksByProject(@PathVariable int projectId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasksByProject(projectId));
        } catch (TaskException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
