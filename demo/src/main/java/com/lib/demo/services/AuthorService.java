package com.lib.demo.services;

import com.lib.demo.dto.AuthorDto;

public interface AuthorService {
    AuthorDto addAuthor(AuthorDto authorDto);
    boolean existsByAuthorName(String authorName);
}
