package com.payx.payxwallet.dto;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
public class ApiErrorResponse {

    private static final Logger logger = LoggerFactory.getLogger(ApiErrorResponse.class);

    private Instant timestamp;
    private int status;
    private String errorCode;
    private String message;
    private String path;
    private List<FieldError> validationErrors;

    public ApiErrorResponse() {
        logger.debug("ApiErrorResponse initialized with default constructor");
    }

    public ApiErrorResponse(Instant timestamp, int status, String errorCode,
                            String message, String path, List<FieldError> validationErrors) {
        this.timestamp = timestamp;
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.path = path;
        this.validationErrors = validationErrors;
        logger.debug("ApiErrorResponse initialized with parameters: timestamp={}, status={}, errorCode={}, message={}, path={}, validationErrors={}",
                timestamp, status, errorCode, message, path, validationErrors);
    }

    public static class FieldError {
        private String field;
        private String message;

        public FieldError() {
            logger.debug("FieldError initialized with default constructor");
        }

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
            logger.debug("FieldError initialized with parameters: field={}, message={}", field, message);
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }

        public void setField(String field) {
            this.field = field;
            logger.debug("Field set to {}", field);
        }

        public void setMessage(String message) {
            this.message = message;
            logger.debug("Message set to {}", message);
        }
    }
}