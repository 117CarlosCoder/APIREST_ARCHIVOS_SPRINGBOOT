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
public class InventoryEntity {
    @Id
    private Long id;
    private Integer pasillo;
    private Integer cantidad;
    private Integer producto_id;
}
