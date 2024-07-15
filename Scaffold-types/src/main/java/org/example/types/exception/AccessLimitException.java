package com.lvzuan.meetmanager.exception;

public class AccessLimitException extends RuntimeException {
    protected AccessLimitException() {
    }

    public AccessLimitException(String message) {
        super(message);
    }

    private AccessLimitException(String message, Throwable ex) {
        super(message, ex);
    }

    public AccessLimitException(Throwable arg0) {
        super(arg0);
    }
}