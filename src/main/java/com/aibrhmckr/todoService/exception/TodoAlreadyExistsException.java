package com.aibrhmckr.todoService.exception;

public class TodoAlreadyExistsException extends Throwable {
    public TodoAlreadyExistsException(String msg){
        super(msg);
    }

}
