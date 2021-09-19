package com.hackathon.runner.exeptions;

public class WrongTestArgumentsException extends Exception {
    public WrongTestArgumentsException(String message) {
        super(message);
    }
}
