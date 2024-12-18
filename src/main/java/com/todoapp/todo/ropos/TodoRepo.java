package com.todoapp.todo.ropos;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.todo.entities.Todo;

public interface TodoRepo extends JpaRepository<Todo, Long>{

    // List<Todo> findAll();


}
