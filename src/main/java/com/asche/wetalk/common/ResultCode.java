package com.asche.wetalk.common;

public enum ResultCode implements IErrorCode{
    SUCCESS(200, "success"),
    FAILED(500, "failed"),
    VALIDATE_FAILED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUNT(404, "not found");


    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
