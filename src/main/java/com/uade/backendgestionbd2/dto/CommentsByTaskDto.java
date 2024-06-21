package com.uade.backendgestionbd2.dto;

import com.uade.backendgestionbd2.model.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentsByTaskDto {
    private String id;
    private Users user;
    private String comment;
    private LocalDate timestamp;
}
