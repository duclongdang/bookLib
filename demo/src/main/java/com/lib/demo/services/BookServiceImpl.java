package com.lib.demo.services;

import com.lib.demo.dto.BookDto;
import com.lib.demo.entities.Author;
import com.lib.demo.entities.Book;
import com.lib.demo.repository.AuthorRepository;
import com.lib.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        Set<Author> authors=new HashSet<>() ;
        for(String authorName : bookDto.getAuthors()) {
            Optional<Author> optional=authorRepository.findByAuthorName(authorName);
            if(optional.isEmpty()) {
                Author author=new Author();
                author.setAuthorName(authorName);
                authorRepository.save(author);
                authors.add(author);
            }
            else authors.add(optional.get());
        }

        Book book = new Book();
        book.setBookTitle(bookDto.getBookTitle());
        book.setPublishedTime(bookDto.getPublishedTime());
        book.setAuthors(authors);
        bookRepository.save(book);
        System.out.println("Book added");
        return bookDto;
    }

    @Override
    public boolean existsBookByTitle(String title) {
        return bookRepository.existsByBookTitle(title);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return List.of();
    }

    @Override
    public BookDto editBook(BookDto bookDto) {
        Set<Author> authors=new HashSet<>() ;
        for(String authorName : bookDto.getAuthors()) {
            Optional<Author> optional=authorRepository.findByAuthorName(authorName);
            if(optional.isEmpty()) {
                Author author=new Author();
                author.setAuthorName(authorName);
                authorRepository.save(author);
                authors.add(author);
            }
            else authors.add(optional.get());
        }
        Book book=bookRepository.findByBookTitle(bookDto.getBookTitle()).get();
        book.setNumberOfPages(bookDto.getNumberOfPages());
        book.setPublishedTime(bookDto.getPublishedTime());
        book.setAuthors(authors);
        bookRepository.save(book);
        System.out.println("Book updated");
        return bookDto;
    }


}
