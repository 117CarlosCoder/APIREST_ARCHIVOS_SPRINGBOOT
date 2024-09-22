package com.archivos.apispringbootcunoc.controller;

import com.archivos.apispringbootcunoc.controller.dto.CreateProductRequest;
import com.archivos.apispringbootcunoc.controller.dto.ValueProductRequest;
import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import com.archivos.apispringbootcunoc.service.WarehouseService;
import com.archivos.apispringbootcunoc.util.JwtUtils;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final JwtUtils jwtUtils;

    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    HttpServletRequest httpServletRequest;

    public WarehouseController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/create-products")
    public void createProducts(@RequestBody CreateProductRequest request) {
        System.out.println("Request  " + request.toString());
         warehouseService.createProduct(request);
    }

    @PostMapping("/getProducts")
    public List<ProductEntity> getClientes(@RequestParam String value) {
        System.out.println("Parametro  " + value);
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        return warehouseService.listProducts(sucursal, value);
    }

    @PostMapping("/add-product")
    public void addProduct(@RequestBody ValueProductRequest request) {
        System.out.println("Request  " + request.toString());
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        warehouseService.addProduct(request, sucursal);
    }

    @PostMapping("/remove-product")
    public void removeProduct(@RequestBody ValueProductRequest request) {
        System.out.println("Request  " + request.toString());
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        warehouseService.removeProduct(request, sucursal);
    }

    @DeleteMapping("/delete-product")
    public void deleteProduct(@RequestParam(required = false) String id, @RequestParam(required = false) String nombre) {
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        warehouseService.deleteProduct(id, nombre, sucursal);
    }

    private String extractJwtFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private String extractSucursalFromToken(String token) {
        try {
            DecodedJWT decodedJWT = jwtUtils.decodeToken(token);
            Map<String, Claim> claims = decodedJWT.getClaims();
            claims.forEach((key, value) -> System.out.println(key + ": " + value.asString()));
            return decodedJWT.getClaim("sucursal").asString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract sucursal from token", e);
        }
    }
}
