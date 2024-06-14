package com.uade.backendgestionbd2.util;

public enum Roles {
    Admin(1, "Admin"),
    Employee(2, "Employee");

    private final int id;
    private final String name;

    Roles(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
