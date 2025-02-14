package com.lib.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Hold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hold_id")
    private Long holdId;
    @Column(name="start_date")
    private LocalDateTime startDate;
    @Column(name="end_date")
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
