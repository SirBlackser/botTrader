package be.ds.projects.model;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponse {
    String error;
    String reason;

    public ErrorResponse(String reason) {
        error = "error";
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
