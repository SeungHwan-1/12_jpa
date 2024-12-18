package com.ohgiraffers.chap06securityjwt.user.model;

public enum OhgiraffersRole {

    USER("USER"),

    ADMIN("ADMIN");

    private String role;

    OhgiraffersRole() {
    }

    @Override
    public String toString() {
        return "OhgiraffersRole{" +
                "role='" + role + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }

    OhgiraffersRole(String role) {
        this.role = role;
    }
}
