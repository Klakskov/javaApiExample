package com.example.demo.exceptions;

import com.example.demo.exceptions.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHanlder extends ResponseEntityExceptionHandler {

    private final HttpStatus httpStatusBusinessException =  HttpStatus.CONFLICT;
    private final HttpStatus httpStatusValidationException =  HttpStatus.BAD_REQUEST;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request) {

        HttpStatus status = httpStatusBusinessException;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;

        Problem problem = Problem.builder()
                .status(status.value())
                .userMessage(ex.getUserMsg())
                .detail(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .title(problemType.getTitle())
                .build();

        return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ValidationRequestException.class)
    public ResponseEntity<Object> handleValidationRequestException(ValidationRequestException ex, WebRequest request) {

        HttpStatus status = httpStatusValidationException;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;

        Problem problem = Problem.builder()
                .status(status.value())
                .userMessage(ex.getUserMsg())
                .detail(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .title(problemType.getTitle())
                .build();

        return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
}
