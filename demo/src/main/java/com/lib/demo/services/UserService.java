package com.lib.demo.services;


import com.lib.demo.dto.user.UserDetailsDto;
import com.lib.demo.dto.user.UserRegisterDto;
import com.lib.demo.entities.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetailsDto registerUser(UserRegisterDto userRegisterDto);

    Boolean existedByEmail(String email);

    Boolean existedByUsername(String username);

    UserDetailsDto getUserByUsername(String username);

    //    UserDetailsDto getUserDetailsByUsernameAndPassword(String username, String password);
    String verify(String username, String password);
}
