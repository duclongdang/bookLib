package com.lib.demo.controllers;

import com.lib.demo.entities.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class RoleController {
    public List<Role> roles=new ArrayList<>(List.of(
            new Role(1L,"ROLE_USER"),
            new Role(2L,"ROLE_ADMIN")
    ));

//    @GetMapping("/roles")
//    public List<Role> getRoles(){
//        System.out.println(roles);
//        return roles;
//    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRolesByName(String name){
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
