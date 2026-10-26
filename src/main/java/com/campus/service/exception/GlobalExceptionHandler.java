package com.campus.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.campus.service.exception.InvalidStaffException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return errors;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidRequestBody(
            HttpMessageNotReadableException ex) {

        Map<String, String> error = new HashMap<>();

        String message = ex.getMostSpecificCause().getMessage();

        if (message != null && message.contains("ComplaintCategory")) {
            error.put("category", "Invalid category. Allowed values: WIFI, EQUIPMENT, HOSTEL, ELECTRICITY, WATER, LAB, LIBRARY, TRANSPORT");
        } else if (message != null && message.contains("Priority")) {
            error.put("priority", "Invalid priority. Allowed values: LOW, MEDIUM, HIGH, CRITICAL");
        } else {
            error.put("error", "Invalid request body. Check that all enum values are valid.");
        }

        return error;
    }

    @ExceptionHandler(InvalidStaffException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidStaff(InvalidStaffException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return error;
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalStateException(
            IllegalStateException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return error;
    }

}