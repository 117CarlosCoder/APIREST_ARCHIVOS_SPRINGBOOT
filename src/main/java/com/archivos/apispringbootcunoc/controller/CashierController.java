package com.archivos.apispringbootcunoc.controller;

import com.archivos.apispringbootcunoc.controller.dto.CreateClientRequest;
import com.archivos.apispringbootcunoc.controller.dto.UpdateClient;
import com.archivos.apispringbootcunoc.persistence.entity.ClientEntity;
import com.archivos.apispringbootcunoc.persistence.entity.ProductEntity;
import com.archivos.apispringbootcunoc.persistence.entity.UserEntity;
import com.archivos.apispringbootcunoc.service.CashierService;
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
@RequestMapping("/cashier")
public class CashierController {

    private final JwtUtils jwtUtils;

    @Autowired
    private CashierService cashierService;
    @Autowired
    HttpServletRequest httpServletRequest;

    public CashierController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/getclients")
    public List<ClientEntity> getClientes() {
        return cashierService.getClientes();
    }

    @GetMapping("/find-clients")
    public List<ClientEntity> searchClient(@RequestParam String nitPattern) {
        System.out.println(nitPattern);
        return cashierService.searhClient(nitPattern);
    }

    @PostMapping("/create-client")
    public void createClientUser(@RequestBody CreateClientRequest request) {
        System.out.println("Request " + request.toString());
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        cashierService.registerClientUser(request,sucursal);
    }

    @PostMapping("/getProducts")
    public List<ProductEntity> getClientes(@RequestParam String value) {
        System.out.println("Parametro  " + value);
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        return cashierService.listProducts(sucursal, value);
    }

    @PutMapping("/update-client")
    public void updateClientUser(@RequestBody UpdateClient request) {
        System.out.println("Request " + request.toString());
        cashierService.updateClientUser(request);
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
