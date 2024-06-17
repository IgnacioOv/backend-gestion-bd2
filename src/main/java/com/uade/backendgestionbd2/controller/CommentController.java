package com.uade.backendgestionbd2.controller;

import com.uade.backendgestionbd2.dto.CommentRequest;
import com.uade.backendgestionbd2.exception.CommentException;
import com.uade.backendgestionbd2.model.Comments;
import com.uade.backendgestionbd2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //add comment
    @PostMapping("/")
    public ResponseEntity<Object> addComment(@RequestBody CommentRequest comment) {
        // Implementación
        Comments comments = new Comments(comment.getTaskId(), comment.getUserId(), comment.getComment(), LocalDate.now());
        commentService.createComment(comments);
        return ResponseEntity.status(HttpStatus.CREATED).body(comments);
    }

    //delete comment
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable String commentId) {
        // Implementación
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body("Comment deleted");
    }

    //get comments by task
    @GetMapping("/{taskId}")
    public ResponseEntity<Object> getCommentsByTask(@PathVariable String taskId) {
        // Implementación
        try {
            List<Comments> comments = commentService.getCommentsByTask(taskId);
            return ResponseEntity.status(HttpStatus.OK).body(comments);
        }catch (CommentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
