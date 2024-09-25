package com.archivos.apispringbootcunoc.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShopEntity {
    @Id
    private Long nit;
    private double totDescuentos;
    private double totsinDescuentos;
    private long cajero;
    private long cliente;
    private String sucursal;
}
