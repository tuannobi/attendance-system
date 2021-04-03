package com.ty.attendancesystem.message;

public class SuccessResponse {
    Object data;
    int code;
    String message;

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
}
