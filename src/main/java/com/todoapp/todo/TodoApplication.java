package com.todoapp.todo;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {
    public static final Logger logger = Logger.getLogger(TodoApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
        
        logger.info("Todo application started successfully!");
    }
}
