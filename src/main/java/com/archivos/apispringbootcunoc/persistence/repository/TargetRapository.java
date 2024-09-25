package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import com.archivos.apispringbootcunoc.persistence.entity.TargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetRapository extends JpaRepository<TargetEntity, Long> {
    @Query(value = "SELECT * FROM admin.clientes_tarjeta WHERE id_cliente = :id", nativeQuery = true)
    TargetEntity userTarget(@Param("id") long id);

}
