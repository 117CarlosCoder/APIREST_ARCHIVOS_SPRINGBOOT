package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    @Query(value = "SELECT * FROM bodega.listar_productos(:sucursal, :filtro)", nativeQuery = true)
    List<ProductEntity> listProduct(@Param("sucursal") String sucursal, @Param("filtro") String filtro);

    void deleteByNombre(String nombre);
    boolean existsByNombre(String nombre);

    @Modifying
    @Transactional
    @Query(value = "CALL bodega.quitar_producto( :nombre, :cantidad, :sucursal)", nativeQuery = true)
    void removeProduct(   @Param("nombre") String nombre,
                          @Param("cantidad") Integer cantidad,
                          @Param("sucursal") String sucursal);

    @Modifying
    @Transactional
    @Query(value = "CALL bodega.agregar_cantidad_producto( :nombre, :cantidad, :sucursal)", nativeQuery = true)
    void addProduct(   @Param("nombre") String nombre,
                          @Param("cantidad") Integer cantidad,
                          @Param("sucursal") String saucursal);

}
