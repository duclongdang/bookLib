package com.lib.demo.services;


import com.lib.demo.dto.user.UserDetailsDto;
import com.lib.demo.dto.user.UserRegisterDto;
import com.lib.demo.entities.Role;
import com.lib.demo.entities.User;
import com.lib.demo.repository.RoleRepository;
import com.lib.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    @Override
    public UserDetailsDto registerUser(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setEmail(userRegisterDto.getEmail());
        user.setStatus("ACTIVE");
        user.setCreatedAt(LocalDateTime.now());
        user.setDateOfBirth(userRegisterDto.getDateOfBirth());

        Role role = roleRepository.findByRoleName("ROLE_USER");
        user.setRole(role);

        userRepository.save(user);

        UserDetailsDto userDetailsDto = UserDetailsDto.builder()
                .username(user.getUsername())
                .password(userRegisterDto.getPassword())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createdAt(user.getCreatedAt())
                .dateOfBirth(user.getDateOfBirth())
                .role(user.getRole().getRoleName())
                .status(user.getStatus())
                .build();

        return userDetailsDto;
    }

    @Override
    public Boolean existedByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existedByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetailsDto getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            return  UserDetailsDto.builder()
//                    .username(user.getUsername())
//                    .password(user.getPassword())
//                    .email(user.getEmail())
//                    .phone(user.getPhone())
//                    .status(user.getStatus())
//                    .role(user.getRole().getRoleName())
//                    .dateOfBirth(user.getDateOfBirth())
//                    .createdAt(user.getCreatedAt())
//                    .build();
//        }
        return null;
    }

    //    @Override
//    public UserDetailsDto getUserDetailsByUsernameAndPassword(String username, String password) {
//        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(username,password);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            return  UserDetailsDto.builder()
//                    .username(user.getUsername())
//                    .password(user.getPassword())
//                    .email(user.getEmail())
//                    .phone(user.getPhone())
//                    .status(user.getStatus())
//                    .role(user.getRole().getRoleName())
//                    .dateOfBirth(user.getDateOfBirth())
//                    .createdAt(user.getCreatedAt())
//                    .build();
//        }
//        return null;
//    }
    @Override
    public String verify(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            User user = userRepository.findByUsername(username).get();
            return jwtService.generateToken(user.getUsername());
        }
        return "failed";
    }
}
