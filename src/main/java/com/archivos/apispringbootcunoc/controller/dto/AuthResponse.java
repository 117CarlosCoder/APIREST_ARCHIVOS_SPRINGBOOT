package com.archivos.apispringbootcunoc.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"message", "status", "jwtToken"})
public record AuthResponse(
        String message,
        String jwtToken,
        Boolean status,
        List<String> roles) {
}
