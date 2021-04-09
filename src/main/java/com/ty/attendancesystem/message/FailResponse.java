package com.ty.attendancesystem.message;

import java.time.LocalDateTime;

public class FailResponse {
    private LocalDateTime time;
    private int code;
    private String message;

    public FailResponse(LocalDateTime time, int code, String message) {
        this.time = time;
        this.code = code;
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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
