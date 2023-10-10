package com.github.lemerch.montelo.exception;

public class CommonMonteloException extends RuntimeException {
    public CommonMonteloException(String message, Exception e) {
        super(message, e);
    }

    public CommonMonteloException(String message) {
        super(message);
    }

    public CommonMonteloException(Exception e) {
        super(e);
    }
}