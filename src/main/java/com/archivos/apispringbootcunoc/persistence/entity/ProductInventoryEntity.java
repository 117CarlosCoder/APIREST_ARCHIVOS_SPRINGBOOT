package com.archivos.apispringbootcunoc.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductInventoryEntity {
    @Id
    private Long id_producto;
    private String nombre_producto;
    private Integer pasillo;
    private Integer cantidad_producto;
    private String sucursal_producto;

}
