package com.antoniosgarbi.exception.details;

public class BadRequestExceptionDetails extends ExceptionDetails {
    public BadRequestExceptionDetails(String details) {
        super(
                "Bad Request Exception",
                400,
                "Check the Documentation",
                null
        );
    }
}