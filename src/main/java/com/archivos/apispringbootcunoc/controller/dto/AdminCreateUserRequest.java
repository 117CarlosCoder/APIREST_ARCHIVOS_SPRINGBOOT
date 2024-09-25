package com.archivos.apispringbootcunoc.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AdminCreateUserRequest(
        @NotBlank String name,
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank Long nit,
        @NotBlank String role) {
}
