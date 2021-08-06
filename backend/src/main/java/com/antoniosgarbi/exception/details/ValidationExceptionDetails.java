package com.antoniosgarbi.exception.details;

public class ValidationExceptionDetails extends ExceptionDetails {

    public ValidationExceptionDetails(String details) {
        super(
                "Error in fields validation",
                400,
                details,
                "Some field(s) wasn't filled in correctly");
    }
}
