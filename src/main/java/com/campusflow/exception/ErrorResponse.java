package com.campusflow.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String timestamp;
    private int status;
    private ErrorCode code;
    private String message;
    private String path;
    private String traceId;
    private Map<String, String> errors;

    public ErrorResponse() {}

    public ErrorResponse(int status, ErrorCode code, String message, String path, Map<String, String> errors, String traceId) {
        this.timestamp = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
        this.status = status;
        this.code = code;
        this.message = message;
        this.path = path;
        this.errors = errors;
        this.traceId = traceId;
    }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public ErrorCode getCode() { return code; }
    public void setCode(ErrorCode code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public Map<String, String> getErrors() { return errors; }
    public void setErrors(Map<String, String> errors) { this.errors = errors; }
    public String getTraceId() { return traceId; }
    public void setTraceId(String traceId) { this.traceId = traceId; }
}
