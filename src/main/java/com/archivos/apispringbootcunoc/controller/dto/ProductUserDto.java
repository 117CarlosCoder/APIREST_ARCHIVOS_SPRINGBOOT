package com.archivos.apispringbootcunoc.controller.dto;

import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductUserDto {
    private Long producto_id;
    private int cantidad;
    private double preciounitario;
}
