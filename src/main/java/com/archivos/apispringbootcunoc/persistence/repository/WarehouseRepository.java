package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WarehouseRepository extends JpaRepository<ProductEntity, Long> {
    void deleteByNombre(String nombre);
    boolean existsByNombre(String nombre);


}
