package com.lib.demo.controllers;

import com.lib.demo.dto.BookDto;
import com.lib.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private BookService bookService;

    @PostMapping("/book/add")
    public ResponseEntity<Object> addBook(@RequestBody BookDto bookDto) {

        return null;
    }
}
