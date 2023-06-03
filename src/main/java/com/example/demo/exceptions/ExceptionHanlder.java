package com.example.demo.exceptions;

import com.example.demo.exceptions.model.BusinessException;
import com.example.demo.exceptions.model.Problem;
import com.example.demo.exceptions.model.ProblemType;
import com.example.demo.exceptions.model.ValidationRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class ExceptionHanlder extends ResponseEntityExceptionHandler {

    private final HttpStatus httpStatusBusinessException =  HttpStatus.CONFLICT;
    private final HttpStatus httpStatusValidationException =  HttpStatus.BAD_REQUEST;

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
    public ResponseEntity<Object> handleBusiness(ValidationRequestException ex, WebRequest request) {

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
}
