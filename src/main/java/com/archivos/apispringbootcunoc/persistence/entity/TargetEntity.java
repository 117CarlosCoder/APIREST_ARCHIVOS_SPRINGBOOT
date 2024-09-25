package com.archivos.apispringbootcunoc.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TargetEntity {
    @Id
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "id_tarjeta")
    private Long idTarjeta;

    @Column(name = "puntos_acumulados")
    private Integer puntosAcumulados;

    @Column(name = "gasto_total")
    private Double gastoTotal;
}
