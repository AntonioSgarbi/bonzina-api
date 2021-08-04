package com.antoniosgarbi.exception.details;

public class NotFoundExceptionDetails extends ExceptionDetails {
    public NotFoundExceptionDetails(String details) {
        super(
                "NotFound Exception",
                404,
                details,
                "You are requesting an invalid path"
        );
    }


}