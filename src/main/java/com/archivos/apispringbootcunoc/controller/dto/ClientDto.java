package com.archivos.apispringbootcunoc.controller.dto;

import lombok.*;

import java.math.BigInteger;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;
    private String email;
    private String name;
    private BigInteger nit;
    private String sucursal;

}
