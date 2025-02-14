package com.lib.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id")
    public Long authorId;
    @Column(name="author_name")
    public String authorName;
    @Column(name="author_description")
    public String authorDescription;
    @Column(name="author_birth")
    public LocalDate authorBirth;
    @Column(name="author_nationality")
    private String authorNationality;
}
