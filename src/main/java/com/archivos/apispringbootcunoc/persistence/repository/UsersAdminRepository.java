package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.persistence.entity.UsersAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsersAdminRepository extends JpaRepository<UsersAdminEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM admin.buscar_usuario(:valor, :sucursal)", nativeQuery = true )
    List<UsersAdminEntity> findUser(
            @Param("valor") String valor,
            @Param("sucursal") String sucursal);
}
