package com.ohgiraffers.chap05springdata.restapi;

import java.util.Date;

public class UserDTO {
    private int no;
    private String id;
    private String pwd;
    private String name;
    private Date enrolldate;

    public UserDTO() {
    }

    public UserDTO(int no, String id, String pwd, String name, Date enrolldate) {
        this.no = no;
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.enrolldate = enrolldate;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEnrolldate() {
        return enrolldate;
    }

    public void setEnrolldate(Date enrolldate) {
        this.enrolldate = enrolldate;
    }
}
