package com.todoapp.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.todo.entities.Todo;
import com.todoapp.todo.ropos.TodoRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class TodoController {
    @Autowired
    private TodoRepo todoRepo;

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return todoRepo.findAll();
    }

    @PostMapping("/savetodos")
    public ResponseEntity<String> saveTodo(@RequestBody Todo todo) {
        try {
            todoRepo.save(todo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Todo saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong while saving your Todo: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTodo(Long id) {
        try {
            todoRepo.deleteById(id);
            return ResponseEntity.ok().body("Todo deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong while deleting your Todo."+ e.getMessage());
        }
    }

    
    @PutMapping("/updatetodo")
    public ResponseEntity<String> updateTodo(@RequestBody Todo updatedTodo) {
        try {
            // Check if the Todo exists
            if (!todoRepo.existsById(updatedTodo.getId())) {
                return ResponseEntity.badRequest().body("Todo with ID " + updatedTodo.getId() + " does not exist.");
            }

            // Save the updated Todo
            todoRepo.save(updatedTodo);
            return ResponseEntity.ok("Todo updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Something went wrong while updating the Todo: " + e.getMessage());
        }
    }
    
}
