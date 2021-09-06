package com.antoniosgarbi.exception.details;

public class RequestMethodNotSupportedExceptionDetails extends ExceptionDetails {
    public RequestMethodNotSupportedExceptionDetails(String details) {
        super(
                "Method not allowed Exception",
                405,
                "The path you are trying to access does not allow this method",
                "Check the Documentation and try another path or method"
        );
    }
}