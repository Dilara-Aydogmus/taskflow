package com.taskflow.service;

import com.taskflow.dto.TaskDTO;
import com.taskflow.entity.Task;
import com.taskflow.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//unit test
class TaskServiceImplTest {

    private TaskRepository taskRepository;
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskServiceImpl(taskRepository);
    }

    @Test
    void createTask_shouldReturnSavedTaskDTO() {
        TaskDTO input = new TaskDTO();
        input.setTitle("Test Görevi");
        input.setDescription("Açıklama");
        input.setStatus("Yeni");

        Task saved = new Task();
        saved.setId(1L);
        saved.setTitle("Test Görevi");
        saved.setDescription("Açıklama");
        saved.setStatus("Yeni");

        when(taskRepository.save(any(Task.class))).thenReturn(saved);

        TaskDTO result = taskService.createTask(input);

        assertNotNull(result);
        assertEquals("Test Görevi", result.getTitle());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void getAllTasks_shouldReturnTaskDTOList() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Desc 1");

        when(taskRepository.findAll()).thenReturn(Collections.singletonList(task));

        List<TaskDTO> tasks = taskService.getAllTasks();

        assertEquals(1, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
    }
}
