package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.exception.CommentException;
import com.uade.backendgestionbd2.model.Comments;
import com.uade.backendgestionbd2.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

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



}
