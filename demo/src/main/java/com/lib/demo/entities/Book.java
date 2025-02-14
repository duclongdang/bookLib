package com.lib.demo.entities;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@Data

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long bookId;
    @Column(unique = true, nullable = false,name="book_title")
    private String bookTitle;
    @Column(name = "published_time")
    private Year publishedTime;
    @Column(name = "number_of_pages")
    private Long numberOfPages;
    @OneToMany(mappedBy = "book", cascade = CascadeType.MERGE)
    private List<BookCopy> bookCopyList;
    @ManyToMany
    @JoinTable(name = "book_author"
            , joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_genres", joinColumns = @JoinColumn(name="book_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;
}
