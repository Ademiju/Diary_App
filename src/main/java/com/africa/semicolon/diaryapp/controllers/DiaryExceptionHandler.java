package com.africa.semicolon.diaryapp.controllers;
import com.africa.semicolon.diaryapp.dtos.responses.ApiResponse;
import com.africa.semicolon.diaryapp.exceptions.DiaryAppException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

@ControllerAdvice
public class DiaryExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({DiaryAppException.class})
    public ResponseEntity<Object> diaryApplicationExceptionHandler(DiaryAppException exception, WebRequest request){
        ApiResponse apiResponse = ApiResponse.builder()
                .message(exception.getMessage())
                .isSuccessful(false)
                .statusCode(400)
                .build();
        return handleExceptionInternal(exception, apiResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exceptionHandler(Exception exception, WebRequest request){
        ApiResponse apiResponse = ApiResponse.builder()
                .message(exception.getMessage())
                .isSuccessful(false)
                .statusCode(400)
                .build();
        return handleExceptionInternal(exception, apiResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> sqlExceptionHandler(SQLException exception, WebRequest request){
        ApiResponse apiResponse = ApiResponse.builder()
                .message(exception.getMessage())
                .isSuccessful(false)
                .statusCode(400)
                .build();
        return handleExceptionInternal(exception, apiResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException exception, WebRequest request){
        ApiResponse apiResponse = ApiResponse.builder()
                .message(exception.getMessage())
                .isSuccessful(false)
                .statusCode(400)
                .build();
        return handleExceptionInternal(exception, apiResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
