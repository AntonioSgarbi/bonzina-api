package com.antoniosgarbi.exception.details;

import java.time.LocalDateTime;

public abstract class ExceptionDetails {
    protected String title;
    protected int status;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timestamp;

    public ExceptionDetails(String title, int status, String details,
                            String developerMessage) {
        this.title = title;
        this.status = status;
        this.details = details;
        this.developerMessage = developerMessage;
        timestamp = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
