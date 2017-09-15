package io.tchepannou.www.academy.login.backend.user;

public class AuthException extends Exception {
    private int statusCode;
    private String errorDetails;

    public AuthException(int statusCode, String errorDetails, Throwable cause){
        super(cause);

        this.statusCode = statusCode;
        this.errorDetails = errorDetails;
    }

    @Override
    public String getMessage() {
        return String.format("code=%s - details=%s", statusCode, errorDetails);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
