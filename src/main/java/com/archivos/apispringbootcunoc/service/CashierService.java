package com.archivos.apispringbootcunoc.service;

import com.archivos.apispringbootcunoc.controller.dto.CreateClientRequest;
import com.archivos.apispringbootcunoc.controller.dto.UpdateClient;
import com.archivos.apispringbootcunoc.persistence.entity.ClientEntity;
import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import com.archivos.apispringbootcunoc.persistence.repository.CashierRepository;
import com.archivos.apispringbootcunoc.persistence.repository.ClientRepository;
import com.archivos.apispringbootcunoc.persistence.repository.ProductRepository;
import com.archivos.apispringbootcunoc.persistence.repository.SearchClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CashierService {
    @Autowired
    private CashierRepository cashierRepository;
    @Autowired
    private ClientRepository createClientRepository;
    @Autowired
    private SearchClientRepository searchClientRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<ClientEntity> getClientes() {
        return cashierRepository.finfByClients();
    }

    public List<ClientEntity> searhClient(String nitPattern) {
        return searchClientRepository.findClientNit(nitPattern);
    }

    public void registerClientUser(CreateClientRequest request, String sucursal) {
        createClientRepository.createClientUser(request.name(), request.username(),"", request.email(), request.nit(), sucursal);
    }

    public void updateClientUser(UpdateClient request) {
        createClientRepository.updateClientUser(request.id(),request.name(), request.username(), request.nit(), request.email());
    }

    public List<ProductEntity> listProducts(String sucursal,String valor) {
        System.out.println("Sucursal: " + sucursal + ", Filtro: " + valor);
        return productRepository.listProduct(sucursal, valor);
    }
}
