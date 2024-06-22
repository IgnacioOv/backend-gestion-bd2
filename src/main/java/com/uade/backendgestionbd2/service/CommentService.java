package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.dto.CommentsByTaskDto;
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
    private UserService userService;

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

    public List<CommentsByTaskDto> getCommentsByTask(String taskId) {
        List<Comments> request = commentRepository.findAllByTask(taskId)
                .orElseThrow(() -> new CommentException("Comments not found"));

        List<CommentsByTaskDto> commentsDto = new ArrayList<>();

        for(Comments comments : request){
            CommentsByTaskDto commentDto = new CommentsByTaskDto();
            commentDto.setId(comments.getId());
            commentDto.setUser(userService.getUserById(Integer.parseInt(comments.getUser_id())));
            commentDto.setComment(comments.getComment());
            commentDto.setTimestamp(comments.getTimestamp());
            commentsDto.add(commentDto);
        }
        return commentsDto;
    }

    public List<Comments> getCommentsByProject(int projectId) {
        List<String> taskIds = taskService.getTaskIdsByProjectId(projectId);
        System.out.println(taskIds.size() + " tasks found");
        List<Comments> comments = new ArrayList<>();

        for (String taskId : taskIds) {
            Optional<List<Comments>> taskComments = commentRepository.findAllByTask(taskId);
            taskComments.ifPresent(comments::addAll);
        }
        System.out.println(comments.size() + " comments found");

        return comments;
    }



}
