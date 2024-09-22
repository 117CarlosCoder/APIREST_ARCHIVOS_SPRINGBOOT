package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value = "SELECT * FROM inventario.listar_productos(:sucursal, :filtro)", nativeQuery = true)
    List<ProductEntity> listProduct(@Param("sucursal") String sucursal, @Param("filtro") String filtro);
}
