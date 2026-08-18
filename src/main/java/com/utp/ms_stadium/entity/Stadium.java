package com.utp.ms_stadium.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stadiums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 150)
    private String address;

    @Column(nullable = false, length = 80)
    private String city;

    @Column(nullable = false)
    private Integer capacity;

    @Column(name = "field_type", nullable = false, length = 30)
    private String fieldType; // sintetico, grass, indoor

    @Column(name = "price_per_hour", nullable = false)
    private Double pricePerHour;

    @Column(name = "owner_email", nullable = false)
    private String ownerEmail; // viene del token JWT (subject)

    @Builder.Default
    @Column(nullable = false)
    private boolean enabled = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}