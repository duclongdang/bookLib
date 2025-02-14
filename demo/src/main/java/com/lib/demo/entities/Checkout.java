package com.lib.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkout_id")
    private Long checkoutId;
    @Column
    private LocalDateTime startDate;
    @Column
    private LocalDateTime endDate;
    @ManyToOne()
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(name="is_returned")
    private Boolean isReturned;
    @Column
    private Long state;
}
