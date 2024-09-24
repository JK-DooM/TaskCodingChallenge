package com.task.cc.controller;

import com.task.cc.model.Task;
import com.task.cc.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public Task saveTask(@RequestBody Task task, Authentication authentication) {
        String username = authentication.getName();
        return taskService.saveTask(task, username);
    }


    @GetMapping("/getall")
    public List<Task> getAllTasksForUser(Authentication authentication) {
        String username = authentication.getName();
        return taskService.getTasksByUser(username);
    }

    @GetMapping("/get/{id}")
    public Task getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable Integer id, @RequestBody Task taskDetails) {
        return taskService.updateTask(id, taskDetails);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }
}
