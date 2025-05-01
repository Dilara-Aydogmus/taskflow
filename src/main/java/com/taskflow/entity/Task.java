package com.taskflow.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Veritabanında görevleri temsil eden JPA entity sınıfıdır.
 * <p>tasks tablosuna karşılık gelir. Her görev başlık, açıklama, durum ve atanmış kullanıcı bilgisi içerir.</p>
 */
@Entity
@Table(name = "tasks")
@Data
public class Task {

    /**
     * Görevin benzersiz kimliği (primary key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Görevin başlığı (zorunlu alan)
     */
    @Column(nullable = false)
    private String title;

    /**
     * Görevin açıklaması (zorunlu alan)
     */
    @Column(nullable = false)
    private String description;

    /**
     * Görevin mevcut durumu (örn: YENİ, DEVAM EDİYOR, TAMAMLANDI)
     */
    @Column
    private String status;

    /**
     * Görevi üstlenen kullanıcının adı
     */
    @Column
    private String assignedTo;
}
