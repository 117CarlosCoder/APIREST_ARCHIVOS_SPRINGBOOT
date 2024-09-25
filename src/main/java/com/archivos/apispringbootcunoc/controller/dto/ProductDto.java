package com.archivos.apispringbootcunoc.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductDto(
        @NotBlank Long producto_id,
        @NotBlank int cantidad,
        @NotBlank double precio_unitario)  {
}
