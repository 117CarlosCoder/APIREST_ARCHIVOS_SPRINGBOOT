package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.controller.dto.ProductDto;
import com.archivos.apispringbootcunoc.controller.dto.ProductUserDto;
import com.archivos.apispringbootcunoc.persistence.entity.ProductInventoryEntity;
import com.archivos.apispringbootcunoc.persistence.entity.ShopEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
    @Query(value = "SELECT * FROM cajero.generar_venta(:nit,:totDescuentos,:totsinDescuentos,:cajero,:cliente, CAST(:productos AS jsonb),:sucursal)", nativeQuery = true)
    void shopProduct(@Param("nit") long nit,
                     @Param("totDescuentos") double totDescuentos,
                     @Param("totsinDescuentos") double totsinDescuentos,
                     @Param("cajero") int cajero,
                     @Param("cliente") int cliente,
                     @Param("productos") String productos,
                     @Param("sucursal") String sucursal);
}