package com.archivos.apispringbootcunoc.service;

import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import com.archivos.apispringbootcunoc.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productoRepository;


}
