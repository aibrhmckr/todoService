package com.aibrhmckr.todoService.service;

import com.aibrhmckr.todoService.exception.TodoNotFoundException;
import com.aibrhmckr.todoService.model.Todo;
import com.aibrhmckr.todoService.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    public List<Todo> getTodos(String user) {
        if(user== null){
            return todoRepository.findAll(); //kullanıcı yoksa bunu kullan bura değişecek ççünkü kullanıcı yoksa giriş
                                                //giriş zaten yapılmayacak, ama kalabilirde boş liste dönüyor çünkü
        }
        else{
            return todoRepository.findByUser(user);
        }
    }

    public Todo createTodo(Todo newTodo) {
        return todoRepository.save(newTodo);//save id si eklenmiş bir şekilde ekler
    }

    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }

    public Todo getTodoById(String id) {
        return todoRepository.findById(id)
        .orElseThrow(() -> new TodoNotFoundException("Todo not found with id:"+id));
    }

    public void updateTodo(String id, Todo newTodo) {
        Todo oldTodo=getTodoById(id);
        oldTodo.setTask(newTodo.getTask());
        todoRepository.save(oldTodo);
    }
}
