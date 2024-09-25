package com.archivos.apispringbootcunoc.controller.dto;

import lombok.*;

import java.math.BigInteger;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSelfDto {
    private Long id_producto;
    private String nombre_producto;
    private int pasillo;
    private int cantidad_producto;
    private String sucursal_producto;
    private String precio_producto;
}
