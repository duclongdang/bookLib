package com.lib.demo.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    @NotBlank
    @Length(min = 5, max = 20)
    private String username;
    @NotBlank(message = "Không được để trống !")
    @Length(min = 8)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).+$",
            message = "Mật khẩu phải chứa ít nhất một chữ thường, một chữ hoa, một chữ số và một ký tự đặc biệt")
    private String password;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @NotBlank
    private String phone;
    @Past
    private LocalDate dateOfBirth;
    private String avatar;

}
