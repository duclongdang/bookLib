package com.lib.demo.controllers;

import com.lib.demo.dto.user.LoginDto;
import com.lib.demo.dto.user.UserDetailsDto;
import com.lib.demo.dto.user.UserRegisterDto;
import com.lib.demo.response.ResponseData;
import com.lib.demo.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class AuthenticationController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello(HttpServletRequest request) {
//        UserRegisterDto userRegisterDto = new UserRegisterDto();
//        userRegisterDto.setUsername("admin");
//        userRegisterDto.setPassword("123456");
//        userRegisterDto.setEmail("admin@gmail.com");
//        userService.registerUser(userRegisterDto);
//        System.out.println("");
        return "Hello World" + " " + request.getSession().getId();
    }
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterDto userRegisterDto, BindingResult bindingResult) {
        UserDetailsDto user =userService.registerUser(userRegisterDto);
        System.out.println(user);
        return new ResponseEntity<>(new ResponseData<>(user, HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value()),HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ResponseData<>(bindingResult.getModel(), HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }

        String isVerified=userService.verify(loginDto.getUsername(),loginDto.getPassword());
        if(isVerified.equals("failed")) {
            return new ResponseEntity<>(new ResponseData<>(), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(new ResponseData<>(isVerified,HttpStatus.OK.getReasonPhrase(),HttpStatus.OK.value()), HttpStatus.OK);
    }
}
