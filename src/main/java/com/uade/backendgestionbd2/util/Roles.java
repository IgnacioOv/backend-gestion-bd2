package com.uade.backendgestionbd2.util;

public enum Roles {
    ADMIN(1, "ADMIN"),
    EMPLOYEE(2, "USER");

    private final int id;
    private final String name;

    Roles(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
