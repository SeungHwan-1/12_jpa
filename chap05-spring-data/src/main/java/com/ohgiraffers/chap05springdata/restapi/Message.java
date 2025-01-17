package com.ohgiraffers.chap05springdata.restapi;

public class Message {
    private int httpStatusCode;
    private String message;

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public Message() {
    }

    public Message(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "httpStatusCode=" + httpStatusCode +
                ", message='" + message + '\'' +
                '}';
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
