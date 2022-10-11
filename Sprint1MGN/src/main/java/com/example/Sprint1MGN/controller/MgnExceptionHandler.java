package com.example.Sprint1MGN.controller;

import com.example.Sprint1MGN.controller.dto.response.Response;
import com.example.Sprint1MGN.controller.dto.response.customException.MgnException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MgnExceptionHandler {
    // 捕捉 MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handler(MethodArgumentNotValidException e) {

        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = allErrors.stream().map(n -> String.valueOf(n.getDefaultMessage()))
                .collect(Collectors.joining(", "));
        Response error = new Response().builder().message(message).build();
        return new ResponseEntity<Response>(error, HttpStatus.OK);
    }

    @ExceptionHandler(MgnException.class)
    public ResponseEntity<Response> handler(MgnException e) {
        Response error = new Response().builder().message(e.getMessage()).build();
        return new ResponseEntity<Response>(error, HttpStatus.OK);
    }

    // 捕捉 ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response> handler(ConstraintViolationException e) {
        Response error = new Response().builder().message(e.getMessage()).build();
        return new ResponseEntity<Response>(error, HttpStatus.OK);
    }

    // 捕捉 Exception
    // 因為是所有例外的父類，可以作為例外處理的最後一道防線
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handler(Exception e) {
        Response error = new Response().builder().message(e.getMessage()).build();
        return new ResponseEntity<Response>(error, HttpStatus.OK);
    }
}
