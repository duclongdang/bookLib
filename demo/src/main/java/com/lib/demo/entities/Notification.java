package com.lib.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notification_id")
    private Long notificationId;
    @Column(name = "sent_at")
    private LocalDateTime sentAt;
    @Column(name="type")
    private String type;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

}
