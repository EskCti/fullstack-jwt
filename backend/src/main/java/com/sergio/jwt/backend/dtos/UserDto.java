package com.sergio.jwt.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String cpfCnpj;
    private String career;
    private String password;
    private String token;

    public String cpfCnpj() {
        return this.cpfCnpj;
    }

    public String password() {
        return this.password;
    }
}
