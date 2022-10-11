package com.example.Sprint1MGN.controller.dto.response.customException;

public class MgnException extends RuntimeException{

    public MgnException() {
    }

    public MgnException(String message) {
        super(message);
    }
}
