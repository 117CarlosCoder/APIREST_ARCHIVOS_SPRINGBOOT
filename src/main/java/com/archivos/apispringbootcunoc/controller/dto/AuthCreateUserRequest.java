package com.archivos.apispringbootcunoc.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String username,
                                    @NotBlank String password,
                                    @NotBlank String Sucursal,
                                    @Valid AuthCreateRoleRequest roleRequest) {
}
