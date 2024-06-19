package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.exception.TaskException;
import com.uade.backendgestionbd2.model.Tasks;
import com.uade.backendgestionbd2.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    // add task
    public void createTask(Tasks task) {
        taskRepository.findByNameAndProject(task.getName(), task.getProject().getProjectId()).ifPresentOrElse(
                t -> {
                    throw new TaskException("Task already exists");
                },() -> {
                    taskRepository.save(task);
                }
        );
    }

    // update task
    public void updateTask(Tasks task) {
        taskRepository.findById(task.getTask_id()).ifPresentOrElse(
                t -> {
                    taskRepository.save(task);
                },() -> {
                    throw new TaskException("Task not found");
                }
        );
    }

    // delete task
    public void deleteTask(int taskId) {
        taskRepository.findById(taskId).ifPresentOrElse(
                t -> {
                    taskRepository.delete(t);
                },() -> {
                    throw new TaskException("Task not found");
                }
        );
    }


    // get task by id
    public Tasks getTaskById(int taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException("Task not found"));
    }

    // get all tasks by project
    public List<Tasks> getAllTasksByProject(int projectId) {
        return taskRepository.findAllByProject(projectId)
                .orElseThrow(() -> new TaskException("Tasks not exist"));
    }

    public List<String> getTaskIdsByProjectId(int projectId) {
        List<Tasks> tasks = taskRepository.findAllByProject(projectId)
                .orElseThrow(() -> new TaskException("Tasks not exist"));

        System.out.println(tasks.size() + " tasks found");

        return tasks.stream()
                .map(task -> String.valueOf(task.getTask_id())) // Convertir Integer a String
                .collect(Collectors.toList());
    }


}
