package com.fawry.coupon.exception;

import com.fawry.coupon.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ApiResponse> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(new ApiResponse(String.format("%s: %s", error.getField(), error.getDefaultMessage())));
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
