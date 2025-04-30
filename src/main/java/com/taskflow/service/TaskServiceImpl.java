package com.taskflow.service;

import com.taskflow.dto.TaskDTO;
import com.taskflow.entity.Task;
import com.taskflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskDTO createTask(TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setAssignedTo(dto.getAssignedTo());

        Task saved = taskRepository.save(task);
        return convertToDTO(saved);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        return convertToDTO(task);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO dto) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus()); //guncellenebilir gorev durumu takibi
        task.setAssignedTo(dto.getAssignedTo());

        Task updated = taskRepository.save(task);
        return convertToDTO(updated);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // DTO - Entity
    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setAssignedTo(task.getAssignedTo());
        return dto;
    }
}
