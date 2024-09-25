package com.archivos.apispringbootcunoc.controller;

import com.archivos.apispringbootcunoc.controller.dto.*;
import com.archivos.apispringbootcunoc.persistence.entity.*;
import com.archivos.apispringbootcunoc.persistence.repository.UserRepository;
import com.archivos.apispringbootcunoc.service.AdminService;
import com.archivos.apispringbootcunoc.service.CashierService;
import com.archivos.apispringbootcunoc.service.UserDetailServiceImpl;
import com.archivos.apispringbootcunoc.util.JwtUtils;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cashier")
public class CashierController {

    private final JwtUtils jwtUtils;

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CashierService cashierService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    private AdminService adminService;

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
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        return cashierService.searhClient(nitPattern, sucursal);
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
    public List<ProductInventoryEntity> getProducts() {
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        return cashierService.listProducts(sucursal);
    }

    @PostMapping("/getTarget")
    public TargetEntity getTerget(@RequestParam int target) {
        System.out.println("Target " + target);
        return cashierService.userTarget(target);
    }


    @PutMapping("/update-client")
    public void updateClientUser(@RequestBody UpdateClient request) {
        System.out.println("Request " + request.toString());
        cashierService.updateClientUser(request);
    }

    @PostMapping("/admin-permission")
    void adminPermission(@RequestBody @Valid AuthLoginRequest userRequest,@RequestParam String id) {

        UserEntity userEntity = userRepository.findUserEntityByUsername(userRequest.username())
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + userRequest.username() + " no existe."));
        userEntity.getRoles().stream()
                .map(role -> role.getRoleEnum().name())
                .findFirst()
                .filter(role -> role.equals("ADMIN"))
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + userRequest.username() + " no tiene permisos de administrador."));

        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);

        if (userEntity.getSucursal().equals(sucursal)) {
            adminService.updateUserTrue(id , sucursal);
        } else {
            throw new RuntimeException("El usuario no tiene permisos para realizar esta acción");
        }

    }

    @PostMapping("/shopUser")
    public void shoUser(@RequestBody ShopUserRequest request) {
        System.out.println("Request " + request);
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String id_user = extractUserIDFromToken(jwtToken);
        System.out.println("id_user " + id_user);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        cashierService.shopProducts(request, id_user, sucursal);
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
