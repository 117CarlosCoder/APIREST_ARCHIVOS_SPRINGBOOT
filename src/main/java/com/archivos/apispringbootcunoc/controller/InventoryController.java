package com.archivos.apispringbootcunoc.controller;

import com.archivos.apispringbootcunoc.controller.dto.InventoryProductRequest;
import com.archivos.apispringbootcunoc.controller.dto.ValueProductRequest;
import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import com.archivos.apispringbootcunoc.persistence.entity.ProductInventoryEntity;
import com.archivos.apispringbootcunoc.service.InventoryService;
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
@RequestMapping("/inventory")
public class InventoryController {

    private final JwtUtils jwtUtils;

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    HttpServletRequest httpServletRequest;

    public InventoryController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/list-products")
    public List<ProductEntity> getProducts(@RequestParam String value) {
        System.out.println("Parametro  " + value);
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        return inventoryService.listProducts(sucursal, value);
    }

    @PostMapping("/move-products")
    public void moveProducts(@RequestBody InventoryProductRequest request) {
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String id_user = extractUserIDFromToken(jwtToken);
        System.out.println("id_user " + id_user);
        inventoryService.moveProducts(request, id_user);
    }

    @PostMapping("/find-products")
    public List<ProductInventoryEntity> findProducts(@RequestParam String valor) {
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        return inventoryService.findProducts(sucursal,valor);
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

    private String extractUserIDFromToken(String token) {
        try {
            DecodedJWT decodedJWT = jwtUtils.decodeToken(token);
            Map<String, Claim> claims = decodedJWT.getClaims();
            claims.forEach((key, value) -> System.out.println(key + ": " + value.asString()));
            return decodedJWT.getClaim("id_user").asString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract id_user from token", e);
        }
    }
}
