package com.uade.backendgestionbd2.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateAssignmentDto {
    private int projectId;
    private int userId;
    private int newUserId;
}
