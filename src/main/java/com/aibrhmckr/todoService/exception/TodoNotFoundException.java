package com.aibrhmckr.todoService.exception;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(String msg){
        super(msg);
    }
}
