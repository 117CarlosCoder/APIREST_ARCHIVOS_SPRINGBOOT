package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.persistence.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "CALL inventario.productos_estanteria( :id, :pasillo, :cantidad ,:producto_id)", nativeQuery = true)
    void addProduct(   @Param("id") Integer id,
                       @Param("pasillo") Integer pasillo,
                       @Param("cantidad") Integer cantidad,
                       @Param("producto_id") Integer producto_id);

}
