package com.uade.backendgestionbd2.util;

public enum Status {
    TO_DO("To Do"),
    IN_ACTIVITIES("In Activities"),
    DONE("Done"),
    CANCELLED("Cancelled"),
    WAITING("Waiting");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
