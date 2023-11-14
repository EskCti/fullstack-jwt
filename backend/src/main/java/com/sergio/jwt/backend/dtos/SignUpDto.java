package com.sergio.jwt.backend.dtos;

public record SignUpDto (
        String firstName,
        String lastName,
        String cpfCnpj,
        String career,
        char[] password
) { }
