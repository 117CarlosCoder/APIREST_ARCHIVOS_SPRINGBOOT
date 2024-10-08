package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.controller.dto.ProductSelfDto;
import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import com.archivos.apispringbootcunoc.persistence.entity.ProductInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InventoryProductSelfRepository extends JpaRepository<ProductInventoryEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM inventario.buscar_producto_estanteria( :valor, :sucursal)", nativeQuery = true)
    List<ProductInventoryEntity> findSelfProducts(
                @Param("valor") String valor,
                @Param("sucursal") String sucursal);


    @Query(value = "SELECT * FROM inventario.listar_productos_estanteria(:sucursal)", nativeQuery = true)
    List<ProductInventoryEntity> listProduct(@Param("sucursal") String sucursal);

}
