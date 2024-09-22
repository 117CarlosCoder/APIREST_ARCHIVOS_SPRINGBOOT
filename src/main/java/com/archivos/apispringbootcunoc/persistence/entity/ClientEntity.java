package com.archivos.apispringbootcunoc.persistence.entity;


import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = " nit")
    private BigInteger nit;

    @Column(name = "sucursal")
    private String sucursal;
}
