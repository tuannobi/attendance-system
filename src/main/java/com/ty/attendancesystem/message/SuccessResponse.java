package com.ty.attendancesystem.message;

import javax.persistence.Entity;

public class SuccessResponse {
    private Object data;
    private int code;
    private String message;

    public SuccessResponse() {
    }

    public SuccessResponse(Object data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public SuccessResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
