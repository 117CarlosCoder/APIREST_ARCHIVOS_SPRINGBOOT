package com.archivos.apispringbootcunoc.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateProductRequest(
        @NotBlank Integer cantidad,
        @NotBlank String nombre,
        @NotBlank Double precio,
        @NotBlank String sucursal)
{
}
