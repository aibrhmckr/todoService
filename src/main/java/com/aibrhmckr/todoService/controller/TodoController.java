package com.aibrhmckr.todoService.controller;

import com.aibrhmckr.todoService.exception.TodoAlreadyExistsException;
import com.aibrhmckr.todoService.exception.TodoNotFoundException;
import com.aibrhmckr.todoService.model.Todo;
import com.aibrhmckr.todoService.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/todos")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;


    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) String user) {
        return new ResponseEntity<>(todoService.getTodos(user), OK);
    }

    @GetMapping("/{Id}")//istenilen veriyi getirir. Bir id gireriz o id ye sahip veriyi getirir
    public ResponseEntity<Todo> getTodo(@PathVariable String Id) {
            return new ResponseEntity<>(getTodoById(Id), OK);
    }


    @PostMapping//obje pushlamak için kulllanılır
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        return new ResponseEntity<>(todoService.createTodo(newTodo), CREATED);

    }

    @PutMapping("/{Id}")//istenileni güncellemek için kullanılır
    public ResponseEntity<Void> getTodo(@PathVariable String Id, @RequestBody Todo newTodo) {

        todoService.updateTodo(Id,newTodo);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String Id) {
        todoService.deleteTodo(Id);
        return new ResponseEntity<>(OK);
    }

    private Todo getTodoById(String Id) {
        return todoService.getTodoById(Id);

    }
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<String> handleTodoNotFoundException(TodoNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
    @ExceptionHandler(TodoAlreadyExistsException.class)
    public ResponseEntity<String> handleTodoAlreadyExistsException(TodoAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }
}
