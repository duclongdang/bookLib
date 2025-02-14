package com.lib.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Table
@Entity
@Data
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_copy_id")
    private Long bookCopyId;
    @Column(name="published_date")
    private LocalDate publishedDate;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name="publisher_id")
    private Publisher publisher;
    @OneToMany(mappedBy = "bookCopy")
    private List<Checkout> checkouts;
    @OneToMany(mappedBy = "bookCopy")
    private List<Hold> holds;
}
