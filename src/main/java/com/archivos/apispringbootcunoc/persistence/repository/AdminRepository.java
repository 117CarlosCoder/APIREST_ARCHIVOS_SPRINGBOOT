package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<UserEntity, Integer> {
    @Modifying
    @Transactional
    @Procedure(procedureName = "admin.registrar_usuario")
    void createUser(@Param("name") String name,
                    @Param("username") String username,
                    @Param("email") String email,
                    @Param("password") String password,
                    @Param("nit") Long nit,
                    @Param("sucursal") String sucursal,
                    @Param("role") String role);

    @Modifying
    @Transactional
    @Procedure(procedureName = "admin.actualiza_usuario_admin")
    void updateUser(@Param("id") Integer id,
                    @Param("name") String name,
                    @Param("email") String email,
                    @Param("nit") Long nit,
                    @Param("username") String username,
                    @Param("update") Boolean update,
                    @Param("sucursal") String sucursal);

}
