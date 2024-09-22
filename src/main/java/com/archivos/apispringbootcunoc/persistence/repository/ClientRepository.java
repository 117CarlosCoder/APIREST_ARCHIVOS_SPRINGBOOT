package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    @Modifying
    @Transactional
    @Procedure(procedureName = "cajero.registrar_cliente")
    void createClientUser(@Param("name") String name,
                          @Param("username") String username,
                          @Param("password") String password,
                          @Param("email") String email,
                          @Param("nit") Long nit,
                          @Param("sucursal") String sucursal);


   /* @Modifying
    @Transactional
    @Procedure(procedureName  = "cajero.actualizar_usuario")
    void updateClientUser(@Param("id") Long id,
                          @Param("name") String name,
                          @Param("email") String email,
                          @Param("nit") Long nit,
                          @Param("username") String username);*/

    @Modifying
    @Transactional
    @Query(value = "CALL cajero.actualizar_usuario(:id, :name, :email, :nit, :username)", nativeQuery = true)
    void updateClientUser(@Param("id") Long id,
                          @Param("name") String name,
                          @Param("username") String username,
                          @Param("nit") Long nit,
                          @Param("email") String email);




}
