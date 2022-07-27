package com.aibrhmckr.todoService.repository;

import com.aibrhmckr.todoService.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo,String> {

    List<Todo> findByUser(String user);

}
