package com.archivos.apispringbootcunoc.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estanteria", schema = "inventario")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pasillo")
    private Integer pasillo;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "producto_id")
    private Integer producto_id;
}
