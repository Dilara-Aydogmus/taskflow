package com.taskflow.dto;

import lombok.Data;


/**
 * Task entity'sinin dış dünyaya (API) sunulan veri transfer nesnesidir.
 * <p>Görev bilgilerini frontend tarafına iletmek veya almak için kullanılır.</p>
 */
@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String assignedTo;
}
