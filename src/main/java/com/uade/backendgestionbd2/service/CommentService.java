package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.exception.CommentException;
import com.uade.backendgestionbd2.model.Comments;
import com.uade.backendgestionbd2.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskService taskService;


    // Métodos del servicio
    public void createComment(Comments comment) {
        // Implementación
        commentRepository.insert(comment);
    }

    public void deleteComment(String commentId) {
        // Implementación
        commentRepository.deleteById(commentId);
    }

    public List<Comments> getCommentsByTask(String taskId) {
        return commentRepository.findAllByTask(taskId)
                .orElseThrow(() -> new CommentException("Comments not found"));
    }

    public List<Comments> getCommentsByProject(int projectId) {
        List<String> taskIds = taskService.getTaskIdsByProjectId(projectId);
        List<Comments> comments = new ArrayList<>();

        for (String taskId : taskIds) {
            Optional<List<Comments>> taskComments = commentRepository.findAllByTask(taskId);
            taskComments.ifPresent(comments::addAll);
        }

        return comments;
    }



}
