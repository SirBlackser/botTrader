package be.ds.projects.model;

public class Error {
    String error;
    String reason;

    public Error(String reason) {
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
