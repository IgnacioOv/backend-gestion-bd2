package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.exception.TaskException;
import com.uade.backendgestionbd2.model.Projects;
import com.uade.backendgestionbd2.model.Tasks;
import com.uade.backendgestionbd2.repository.TaskRepository;
import com.uade.backendgestionbd2.util.SkillLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectService projectService;


    // add task
    public void createTask(Tasks task) {
        taskRepository.findByNameAndProject(task.getName(), task.getProject().getProjectId()).ifPresentOrElse(
                t -> {
                    throw new TaskException("Task already exists");
                },() -> {
                    //comprobar cantida de horas del proyecto
                    int hours = task.getProject().getProjectId();
                    //comprobar cantidad de horas del usuario
                    int user_hours = task.getUser().getWeekly_hours();


                    // todos los proyectos del usuario
                    List<Projects> projects = projectService.getProjectsByUserId(task.getUser().getUser_id());
                    // todas las tareas del usuario
                    //sumas horas
                    int sum = projects.stream()
                            .map(Projects::getWeeklyHours)
                            .reduce(0, Integer::sum);
                    if (sum + hours > user_hours) {
                        throw new TaskException("User does not have enough hours");
                    }
                    // Verificar la compatibilidad de habilidades
                    if (!isSkillLevelCompatible(task.getSkillLevel(), task.getUser().getSkillLevel())) {
                        throw new TaskException("User does not have the required skill level for this task");
                    }
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
    private boolean isSkillLevelCompatible(SkillLevel required, SkillLevel userSkill) {
        EnumMap<SkillLevel, Integer> skillHierarchy = new EnumMap<>(SkillLevel.class);

        // Backend roles
        skillHierarchy.put(SkillLevel.BACKEND_JUNIOR, 1);
        skillHierarchy.put(SkillLevel.BACKEND_MID, 2);
        skillHierarchy.put(SkillLevel.BACKEND_SENIOR, 3);

        // Frontend roles
        skillHierarchy.put(SkillLevel.FRONTEND_JUNIOR, 1);
        skillHierarchy.put(SkillLevel.FRONTEND_MID, 2);
        skillHierarchy.put(SkillLevel.FRONTEND_SENIOR, 3);

        // Fullstack roles
        skillHierarchy.put(SkillLevel.FULLSTACK_JUNIOR, 1);
        skillHierarchy.put(SkillLevel.FULLSTACK_MID, 2);
        skillHierarchy.put(SkillLevel.FULLSTACK_SENIOR, 3);

        // DevOps roles
        skillHierarchy.put(SkillLevel.DEVOPS_JUNIOR, 1);
        skillHierarchy.put(SkillLevel.DEVOPS_MID, 2);
        skillHierarchy.put(SkillLevel.DEVOPS_SENIOR, 3);

        // Verificar si el rol es el mismo y si el nivel del usuario es igual o superior al requerido
        if (getRole(required).equals(getRole(userSkill))) {
            return skillHierarchy.get(userSkill) >= skillHierarchy.get(required);
        }
        return false;
    }

    private String getRole(SkillLevel skillLevel) {
        return skillLevel.name().split("_")[0];
    }


}
