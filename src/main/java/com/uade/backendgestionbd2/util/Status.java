package com.uade.backendgestionbd2.util;

public enum Status {
    To_Do("To Do"),
    In_Activities("In Activities"),
    Done("Done"),
    Cancelled("Cancelled"),
    Waiting("Waiting");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
