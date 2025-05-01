package com.taskflow.service;

import java.util.Optional;
import com.taskflow.entity.User;
import com.taskflow.dto.TaskDTO;
import com.taskflow.entity.Task;
import com.taskflow.repository.TaskRepository;
import com.taskflow.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        userRepository = mock(UserRepository.class);
        taskService = new TaskServiceImpl(taskRepository, userRepository);
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

    @Test
    void createTask_withAssignedTo_shouldSetUser() {
        // DTO nesnesi oluşturuluyor
        TaskDTO input = new TaskDTO();
        input.setTitle("Test Görevi");
        input.setDescription("Açıklama");
        input.setStatus("Yeni");
        input.setAssignedTo("testuser");  // kullanıcı adı DTO'da var

        // Mock user oluşturuluyor
        User mockUser = new User();
        mockUser.setId(42L);
        mockUser.setUsername("testuser");

        // Mock task (save sonrası dönecek olan)
        Task saved = new Task();
        saved.setId(1L);
        saved.setTitle("Test Görevi");
        saved.setDescription("Açıklama");
        saved.setStatus("Yeni");
        saved.setAssignedTo(mockUser);

        // Mock davranışlar
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(mockUser));
        when(taskRepository.save(any(Task.class))).thenReturn(saved);

        // Servis çağrılıyor
        TaskDTO result = taskService.createTask(input);

        // Doğrulamalar
        assertNotNull(result);
        assertEquals("testuser", result.getAssignedTo());
        assertEquals(42L, result.getAssignedToId());
        verify(userRepository, times(1)).findByUsername("testuser");
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void updateTask_withAssignedTo_shouldUpdateUser() {
        Long taskId = 1L;

        // Güncellenecek DTO
        TaskDTO dto = new TaskDTO();
        dto.setTitle("Güncel Görev");
        dto.setDescription("Güncellenmiş açıklama");
        dto.setStatus("Devam Ediyor");
        dto.setAssignedTo("newuser");

        // Mevcut task (önceki hali)
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTitle("Eski Başlık");
        existingTask.setDescription("Eski Açıklama");
        existingTask.setStatus("Yeni");

        // Yeni atanan kullanıcı
        User newUser = new User();
        newUser.setId(99L);
        newUser.setUsername("newuser");

        // Güncellenmiş task
        Task updatedTask = new Task();
        updatedTask.setId(taskId);
        updatedTask.setTitle("Güncel Görev");
        updatedTask.setDescription("Güncellenmiş açıklama");
        updatedTask.setStatus("Devam Ediyor");
        updatedTask.setAssignedTo(newUser);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(userRepository.findByUsername("newuser")).thenReturn(Optional.of(newUser));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        TaskDTO result = taskService.updateTask(taskId, dto);

        assertEquals("newuser", result.getAssignedTo());
        assertEquals(99L, result.getAssignedToId());
        assertEquals("Güncel Görev", result.getTitle());
        verify(taskRepository, times(1)).save(any(Task.class));
        verify(userRepository, times(1)).findByUsername("newuser");
    }

}
