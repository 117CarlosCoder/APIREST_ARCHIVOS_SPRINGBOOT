package com.archivos.apispringbootcunoc.service;

import com.archivos.apispringbootcunoc.controller.dto.CreateProductRequest;
import com.archivos.apispringbootcunoc.controller.dto.ValueProductRequest;
import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import com.archivos.apispringbootcunoc.persistence.repository.ProductRepository;
import com.archivos.apispringbootcunoc.persistence.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<ProductEntity> listProducts(String sucursal, String valor) {
        System.out.println("Sucursal: " + sucursal + ", Filtro: " + valor);
        return productRepository.listProduct(sucursal, valor);
    }

    public void createProduct(CreateProductRequest request) {
        ProductEntity productEntity = new ProductEntity(null, request.nombre(), request.cantidad(), request.sucursal(), request.precio());
        System.out.println("ProductEntity: " + productEntity.toString());
        productRepository.save(productEntity);
    }

    public void removeProduct(ValueProductRequest request, String sucursal) {


        productRepository.findById(Long.valueOf(request.id())).ifPresentOrElse(
                productEntity -> {
                    if (productEntity.getSucursal().equals(sucursal)) {
                        productRepository.removeProduct(request.nombre(), request.cantidad(), sucursal);

                    } else {
                        throw new RuntimeException("No tienes permisos para remover este producto");
                    }
                },
                () -> {
                }
        );
    }

    public void addProduct(ValueProductRequest request, String sucursal) {


        productRepository.findById(Long.valueOf(request.id())).ifPresentOrElse(
                productEntity -> {
                    if (productEntity.getSucursal().equals(sucursal)) {
                        productRepository.addProduct(request.nombre(), request.cantidad(), sucursal);

                    } else {
                        throw new RuntimeException("No tienes permisos para remover este producto");
                    }
                },
                () -> {
                }
        );
    }

    public void deleteProduct(String id, String nombre, String sucursal) {
        productRepository.findById(Long.valueOf(id)).ifPresentOrElse(
                productEntity -> {
                    if (productEntity.getSucursal().equals(sucursal)) {
                            Long idLong = Long.valueOf(id);
                            if (productRepository.existsById(idLong)) {
                                productRepository.deleteById(idLong);
                            } else {
                                if (nombre != null) {
                                    if (productRepository.existsByNombre(nombre)) {
                                        productRepository.deleteByNombre(nombre);
                                    } else {
                                        throw new RuntimeException("Producto no encontrado con nombre: " + nombre);
                                    }
                                }
                                throw new RuntimeException("Producto no encontrado con ID: " + id);
                            }

                    } else {
                        throw new RuntimeException("No tienes permisos para eliminar este producto");
                    }
                },
                () ->  {
                    throw new IllegalArgumentException("Debes proporcionar un ID o un nombre");
                }
        );

    }
}
