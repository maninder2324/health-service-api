package com.health.api.common;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    private int status = HttpStatus.OK.value();
    private String message = "";
    private Object content = "";

    public ApiResponse() {
        this(HttpStatus.OK.value());
    }

    public ApiResponse(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public ApiResponse status(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResponse message(String message) {
        this.message = message;
        return this;
    }

    public Object getContent() {
        return content;
    }

    public ApiResponse content(Object content) {
        this.content = content;
        return this;
    }

    public static ApiResponse ok() {
        return new ApiResponse();
    }

    public static ApiResponse ok(Object content) {
        return new ApiResponse().content(content);
    }

    public static ApiResponse error() {
        return new ApiResponse(HttpStatus.BAD_REQUEST.value());
    }

    public static ApiResponse error(String message) {
        return new ApiResponse(HttpStatus.BAD_REQUEST.value()).message(message);
    }

}
