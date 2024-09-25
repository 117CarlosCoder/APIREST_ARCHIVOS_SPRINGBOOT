package com.archivos.apispringbootcunoc.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ShopUserRequest(
        @NotBlank Long nit,
        @NotBlank double totDescuentos,
        @NotBlank double totsinDescuentos,
        @NotBlank int cliente,
        @NotBlank List<ProductDto> productos) {
}
