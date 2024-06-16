package com.uade.backendgestionbd2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    private String name;
    private String description;
    private int project;
    private LocalDate startDate;
    private LocalDate endDate;
    private int status;

}
