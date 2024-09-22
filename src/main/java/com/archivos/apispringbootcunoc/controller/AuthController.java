package com.archivos.apispringbootcunoc.controller;

import com.archivos.apispringbootcunoc.controller.dto.AuthCreateUserRequest;
import com.archivos.apispringbootcunoc.controller.dto.AuthLoginRequest;
import com.archivos.apispringbootcunoc.controller.dto.AuthResponse;
import com.archivos.apispringbootcunoc.persistence.entity.UserEntity;
import com.archivos.apispringbootcunoc.persistence.repository.UserRepository;
import com.archivos.apispringbootcunoc.service.UserDetailServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid AuthLoginRequest userRequest){
        AuthResponse authResponse = this.userDetailService.loginUser(userRequest);

        UserEntity userEntity = userRepository.findUserEntityByUsername(userRequest.username())
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + userRequest.username() + " no existe."));

        String firstRole = userEntity.getRoles().stream()
                .map(role -> role.getRoleEnum().name())
                .findFirst()
                .orElse("NO_ROLE");

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("role", firstRole);

        ResponseCookie jwtCookie = ResponseCookie.from("jwtToken", authResponse.jwtToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("None")
                .maxAge(24 * 60 * 60)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(responseBody);
    }

    @PostMapping(value = "logout")
    public ResponseEntity<String> logout( Response response)
    {
        return this.userDetailService.logout(response);
    }
}
