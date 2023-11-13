package com.sergio.jwt.backend.dtos;

public record SignUpDto (String firstName, String lastName, String login, String cpfCnpj, char[] password) { }
