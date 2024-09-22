package com.archivos.apispringbootcunoc.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ValueProductRequest(
        @NotBlank Integer id,
        @NotBlank String nombre,
        @NotBlank Integer cantidad) {
}
