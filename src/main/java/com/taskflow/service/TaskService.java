package com.taskflow.service;

import com.taskflow.dto.TaskDTO;
import java.util.List;

/**
 * Görev işlemleri için servis katmanı arayüzüdür.
 * <p>
 * Uygulamanın iş mantığını temsil eder ve implementasyonu {@link TaskServiceImpl} sınıfında yapılır.
 */
public interface TaskService {

    TaskDTO createTask(TaskDTO dto);
    List<TaskDTO> getAllTasks();
    TaskDTO getTaskById(Long id);
    TaskDTO updateTask(Long id, TaskDTO dto);
    void deleteTask(Long id);
}
