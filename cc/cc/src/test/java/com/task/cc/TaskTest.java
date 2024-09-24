package com.task.cc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.task.cc.enums.Priority;
import com.task.cc.enums.Status;
import com.task.cc.model.Task;
import com.task.cc.repository.TaskRepository;
import com.task.cc.service.TaskService;

@SpringBootTest
public class TaskTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    @Test
    public void createTaskTest() {
        Task task = new Task();
        task.setTitle("Sample Task");
        task.setDescription("Task description");
        task.setDueDate("2024-09-30");
        task.setPriority(Priority.HIGH); 
        task.setStatus(Status.PENDING);
        when(taskRepository.save(task)).thenReturn(task); 
        
        Task savedTask = taskService.saveTask(task,"check"); 
        assertNotNull(savedTask); 
    }

}
