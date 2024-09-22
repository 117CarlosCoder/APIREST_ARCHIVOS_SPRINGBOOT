package com.archivos.apispringbootcunoc.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record InventoryProductRequest(
        @NotBlank Integer pasillo,
        @NotBlank Integer cantidad,
        @NotBlank Integer producto_id) {
}
