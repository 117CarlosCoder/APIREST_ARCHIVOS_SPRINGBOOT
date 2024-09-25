package com.archivos.apispringbootcunoc.service;

import com.archivos.apispringbootcunoc.controller.dto.CreateClientRequest;
import com.archivos.apispringbootcunoc.controller.dto.ProductSelfDto;
import com.archivos.apispringbootcunoc.controller.dto.ShopUserRequest;
import com.archivos.apispringbootcunoc.controller.dto.UpdateClient;
import com.archivos.apispringbootcunoc.persistence.entity.ClientEntity;
import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import com.archivos.apispringbootcunoc.persistence.entity.ProductInventoryEntity;
import com.archivos.apispringbootcunoc.persistence.entity.TargetEntity;
import com.archivos.apispringbootcunoc.persistence.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private InventoryProductSelfRepository inventoryProductSelfRepository;
    @Autowired
    private TargetRapository targetRapository;
    @Autowired
    private ShopRepository shopRepository;

    public List<ClientEntity> getClientes() {
        return cashierRepository.finfByClients();
    }

    public List<ClientEntity> searhClient(String nitPattern, String sucursal) {
        return searchClientRepository.findClientNit(nitPattern, sucursal);
    }

    public void registerClientUser(CreateClientRequest request, String sucursal) {
        createClientRepository.createClientUser(request.name(), request.username(),"", request.email(), request.nit(), sucursal);
    }

    public void updateClientUser(UpdateClient request) {
        createClientRepository.updateClientUser(request.id(),request.name(), request.username(), request.nit(), request.email());
    }

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode productosJson;



    public void shopProducts(ShopUserRequest request, String cajero ,String sucursal) {
        try {
            productosJson = objectMapper.valueToTree(request.productos()); // Convierte la lista a JsonNode
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Productos: " + productosJson.toString());
        shopRepository.shopProduct(request.nit(), request.totDescuentos(), request.totsinDescuentos(),Integer.parseInt(cajero), request.cliente(), productosJson.toString(),sucursal );
    }

    public TargetEntity userTarget(int id) {
        System.out.println("Id: " + id);
        return targetRapository.userTarget(id);
    }

    public List<ProductInventoryEntity> listProducts(String sucursal) {
        System.out.println("Sucursal: " + sucursal );
        return inventoryProductSelfRepository.listProduct(sucursal);
    }
}
