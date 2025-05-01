package com.taskflow.repository;

import com.taskflow.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Task entity'si için veritabanı erişim katmanıdır.
 * <p>Spring Data JPA sayesinde CRUD işlemleri otomatik olarak sağlanır.</p>
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Gerekirse özel sorgular buraya eklenebilir
}
