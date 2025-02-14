package com.lib.demo.dto.user;

import com.lib.demo.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDto {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String status;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private String role;
}
