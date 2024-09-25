package com.archivos.apispringbootcunoc.controller;

import com.archivos.apispringbootcunoc.controller.dto.AdminCreateUserRequest;
import com.archivos.apispringbootcunoc.controller.dto.AdminUpdateUserRequest;
import com.archivos.apispringbootcunoc.controller.dto.AuthLoginRequest;
import com.archivos.apispringbootcunoc.controller.dto.AuthResponse;
import com.archivos.apispringbootcunoc.persistence.entity.UserEntity;
import com.archivos.apispringbootcunoc.service.AdminService;
import com.archivos.apispringbootcunoc.util.JwtUtils;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final JwtUtils jwtUtils;

    @Autowired
    private AdminService adminService;

    @Autowired
    HttpServletRequest httpServletRequest;

    public AdminController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/get-report")
    void getReports () {
        System.out.println("Getting reports");
    }

    @PostMapping("/create-user")
    void createUser (@RequestBody AdminCreateUserRequest request) {
        System.out.println("Creating user");
        System.out.println("Request " + request.toString());
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        adminService.createUser(request, sucursal);
    }

    @PostMapping("/update-user")
    void updateUser(@RequestBody AdminUpdateUserRequest request){
        System.out.println("Updating user");
        System.out.println("Request " + request.toString());
        String jwtToken = extractJwtFromCookie(httpServletRequest);
        System.out.println("Token " + jwtToken);
        String sucursal = extractSucursalFromToken(jwtToken);
        System.out.println("suscursal " + sucursal);
        adminService.updateUser(request , sucursal);
    }



    @PostMapping("/create-targets")
    void updateTargets(){
        System.out.println("Updating Targets");
    }

    @PostMapping("/delete-user")
    void deleteUser(){
        System.out.println("Deleting user");
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
