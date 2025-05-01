package com.taskflow.controller;

import com.taskflow.dto.TaskDTO;
import com.taskflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Görevlerle ilgili API işlemlerini yöneten denetleyici sınıftır.
 * <p>Görev oluşturma, listeleme, güncelleme ve silme işlemleri içerir.</p>
 */
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * Yeni bir görev oluşturur.
     *
     * @param taskDTO Oluşturulacak görev verisi
     * @return Oluşturulan görev bilgisi
     */
    @PostMapping("/save")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createTask(taskDTO));
    }

    /**
     * Sistemde kayıtlı tüm görevleri getirir.
     *
     * @return Görevlerin listesi
     */
    @GetMapping("/findAll")
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * Belirli bir ID'ye sahip görevi getirir.
     *
     * @param id Görev kimliği
     * @return İlgili görev verisi
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    /**
     * Mevcut bir görevi günceller.
     *
     * @param id        Güncellenecek görevin kimliği
     * @param taskDTO   Yeni görev verisi
     * @return Güncellenmiş görev verisi
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDTO));
    }

    /**
     * Görevi veritabanından siler.
     *
     * @param id Silinecek görevin kimliği
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
