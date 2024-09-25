package com.archivos.apispringbootcunoc.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AdminUpdateUserRequest(
        @NotBlank Integer id,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank Long nit,
        @NotBlank String username,
        @NotBlank Boolean update) {
}
