package com.task.cc.service;

import com.task.cc.model.Task;
import com.task.cc.model.User;
import com.task.cc.repository.TaskRepository;
import com.task.cc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired	
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task saveTask(Task task, String username) {
        User user = userRepository.getUserInfoByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUser(String username) {
        User user = userRepository.getUserInfoByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return taskRepository.findByUser(user);
    }

    public Task getTaskById(Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return task.get();
        } else {
            throw new RuntimeException("Task not found");
        }
    }
    
    public Task updateTask(Integer id, Task taskDetails) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);

        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();
            
            existingTask.setTitle(taskDetails.getTitle());
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setDueDate(taskDetails.getDueDate());
            existingTask.setPriority(taskDetails.getPriority());
            existingTask.setStatus(taskDetails.getStatus());

            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task not found with id: " + id);
        }
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
