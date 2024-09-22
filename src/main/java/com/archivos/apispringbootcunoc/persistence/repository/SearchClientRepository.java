package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SearchClientRepository extends CrudRepository<ClientEntity, Long> {
    @Query(value = "SELECT * FROM cajero.buscar_usuario_nit(:p_nit_pattern)", nativeQuery = true)
    List<ClientEntity> findClientNit(@Param("p_nit_pattern") String nitPattern);
}
