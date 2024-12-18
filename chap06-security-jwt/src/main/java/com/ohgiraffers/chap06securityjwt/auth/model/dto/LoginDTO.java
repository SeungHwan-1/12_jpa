package com.ohgiraffers.chap06securityjwt.auth.model.dto;

public class LoginDTO {

    private String id;
    private String pass;

    public LoginDTO() {
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "id='" + id + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LoginDTO(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }
}
