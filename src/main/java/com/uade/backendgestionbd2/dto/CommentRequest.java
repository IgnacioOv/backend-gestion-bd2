package com.uade.backendgestionbd2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommentRequest {
    private String taskId;
    private String userId;
    private String comment;

    public CommentRequest() {
    }

    public CommentRequest(String taskId,String userId, String comment) {
        this.taskId = taskId;
        this.userId = userId;
        this.comment = comment;
    }
}
