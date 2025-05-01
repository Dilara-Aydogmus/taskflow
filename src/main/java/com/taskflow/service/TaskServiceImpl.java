package com.taskflow.service;

import com.taskflow.dto.TaskDTO;
import com.taskflow.entity.Task;
import com.taskflow.entity.User;

import com.taskflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.taskflow.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TaskService arayüzünün görev iş mantığını gerçekleştiren sınıfıdır.
 * <p>Görev oluşturma, listeleme, getirme, güncelleme ve silme işlemlerini içerir.</p>
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private String assignedTo;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    /**
     * Yeni bir görev oluşturur.
     *
     * @param dto Görev verisi
     * @return Kaydedilen görev DTO'su
     */
    @Override
    public TaskDTO createTask(TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());

        // 👇 Buraya ekle: Kullanıcı adından User nesnesi bulup set et
        if (dto.getAssignedTo() != null && !dto.getAssignedTo().isEmpty()) {
            User user = userRepository.findByUsername(dto.getAssignedTo())
                    .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + dto.getAssignedTo()));
            task.setAssignedTo(user);
        }

        Task saved = taskRepository.save(task);
        return convertToDTO(saved);
    }
    /**
     * Tüm görevleri getirir.
     *
     * @return Görevlerin DTO listesi
     */
    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Belirli bir ID'ye sahip görevi getirir.
     *
     * @param id Görev kimliği
     * @return Görev DTO'su
     */
    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        return convertToDTO(task);
    }

    /**
     * Mevcut görevi günceller.
     *
     * @param id  Güncellenecek görev ID'si
     * @param dto Yeni görev verileri
     * @return Güncellenmiş görev DTO'su
     */
    @Override
    public TaskDTO updateTask(Long id, TaskDTO dto) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());


        if (dto.getAssignedTo() != null && !dto.getAssignedTo().isEmpty()) {
            User user = userRepository.findByUsername(dto.getAssignedTo())
                    .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + dto.getAssignedTo()));
            task.setAssignedTo(user);
        }

        Task updated = taskRepository.save(task);
        return convertToDTO(updated);
    }
    /**
     * Belirli bir ID'ye sahip görevi siler.
     *
     * @param id Silinecek görev ID'si
     */
    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    /**
     * Task entity nesnesini TaskDTO'ya çevirir.
     *
     * @param task Entity nesnesi
     * @return DTO nesnesi
     */
    // DTO - Entity
    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());

        if (task.getAssignedTo() != null) {
            dto.setAssignedToId(task.getAssignedTo().getId());
            dto.setAssignedTo(task.getAssignedTo().getUsername()); //
        }

        return dto;
    }
}
