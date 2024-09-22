package com.archivos.apispringbootcunoc.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigInteger;

public record CreateClientRequest(
                                    @NotBlank String name,
                                    @NotBlank String username,
                                    @NotBlank String email,
                                    @NotBlank Long nit) {
}
