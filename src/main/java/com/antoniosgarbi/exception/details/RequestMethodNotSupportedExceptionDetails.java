package com.antoniosgarbi.exception.details;

public class RequestMethodNotSupportedExceptionDetails extends ExceptionDetails {
    public RequestMethodNotSupportedExceptionDetails(String details) {
        super(
                "Method not allowed Exception",
                405,
                "Check the Documentation and try another path or method",
                null
        );
    }
}