package com.archivos.apispringbootcunoc.service;

import com.archivos.apispringbootcunoc.controller.dto.InventoryProductRequest;
import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import com.archivos.apispringbootcunoc.persistence.entity.ProductInventoryEntity;
import com.archivos.apispringbootcunoc.persistence.repository.InventoryProductRepository;
import com.archivos.apispringbootcunoc.persistence.repository.InventoryProductSelfRepository;
import com.archivos.apispringbootcunoc.persistence.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryProductRepository inventoryProductRepository;

   @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryProductSelfRepository inventoryProductSelfRepository;

    public void moveProducts(InventoryProductRequest request, String user_id) {
        inventoryRepository.addProduct(Integer.parseInt(user_id), request.pasillo(), request.cantidad(), request.producto_id());
    }

    public List<ProductEntity> listProducts(String sucursal, String valor) {
        System.out.println("Sucursal: " + sucursal + ", Filtro: " + valor);
        return inventoryProductRepository.listProduct(sucursal, valor);
    }

    public List<ProductInventoryEntity> findProducts(String sucursal, String valor) {
        System.out.println("Sucursal: " + sucursal + ", Filtro: " + valor);
        return inventoryProductSelfRepository.findSelfProducts(valor, sucursal);
    }
}
