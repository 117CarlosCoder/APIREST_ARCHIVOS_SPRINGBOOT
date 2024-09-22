package com.archivos.apispringbootcunoc.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigInteger;
import java.text.Bidi;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClientEntity {
    @Id
    private Long id;
    private String email;
    private String name;
    private BigInteger nit;
    private String sucursal;
}
