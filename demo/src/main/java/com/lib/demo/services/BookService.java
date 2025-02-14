package com.lib.demo.services;

import com.lib.demo.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto addBook(BookDto bookDto);
    boolean existsBookByTitle(String title);
    List<BookDto> getAllBooks();
    BookDto editBook(BookDto book);
}
