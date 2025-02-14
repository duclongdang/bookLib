package com.lib.demo.repository;

import com.lib.demo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //    Optional<Book> findByBookId(Long bookId);
    Optional<Book> findByBookTitle(String bookTitle);

    List<Book> findAll();

    boolean existsByBookTitle(String bookTitle);
}
