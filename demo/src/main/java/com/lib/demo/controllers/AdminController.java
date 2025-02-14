package com.lib.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {

        return null;
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        return null;
    }
}
