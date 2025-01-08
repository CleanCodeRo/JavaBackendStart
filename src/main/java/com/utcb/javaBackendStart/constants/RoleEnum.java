package com.utcb.javaBackendStart.constants;

public enum RoleEnum {
    WORKER("WORKER"),
    DIRECTOR("DIRECTOR"),
    ACCOUNTANT("ACCOUNTANT"),
    ADMIN("ADMIN");

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
