package com.github.lemerch.montelo.exception;

public class UserException extends CommonMonteloException{
    public UserException(String message, Exception e) {
        super(message, e);
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(Exception e) {
        super(e);
    }
}
