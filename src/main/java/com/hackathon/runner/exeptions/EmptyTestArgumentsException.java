package com.hackathon.runner.exeptions;

public class EmptyTestArgumentsException extends Exception {
    public EmptyTestArgumentsException() {
        super("If you want to run tests you need to set arguments");
    }
}
