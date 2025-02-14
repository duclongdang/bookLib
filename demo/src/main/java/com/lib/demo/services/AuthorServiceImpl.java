package com.lib.demo.services;

import com.lib.demo.dto.AuthorDto;
import com.lib.demo.entities.Author;
import com.lib.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto addAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setAuthorName(authorDto.getAuthorName());
        author.setAuthorNationality(authorDto.getAuthorNationality());
        authorRepository.save(author);
        return authorDto;
    }

    @Override
    public boolean existsByAuthorName(String authorName) {
        return authorRepository.existsByAuthorName(authorName);
    }
}
