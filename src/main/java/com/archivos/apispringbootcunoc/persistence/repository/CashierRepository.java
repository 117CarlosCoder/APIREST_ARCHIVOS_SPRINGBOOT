package com.archivos.apispringbootcunoc.persistence.repository;

import com.archivos.apispringbootcunoc.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface CashierRepository extends CrudRepository<ClientEntity, Long> {
    @Query(value = "SELECT * FROM cajero.vistausuarios", nativeQuery = true)
    List<ClientEntity> finfByClients();
}
