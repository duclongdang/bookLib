package com.lib.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publisher_id")
    private Long publisherId;
    @Column(name = "publisher_name")
    private String publisherName;
}
