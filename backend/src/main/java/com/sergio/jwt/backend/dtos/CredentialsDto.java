package com.sergio.jwt.backend.dtos;

public record CredentialsDto (String cpfCnpj, char[] password) { }