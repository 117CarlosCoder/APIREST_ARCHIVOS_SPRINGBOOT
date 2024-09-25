package com.archivos.apispringbootcunoc.service;

import com.archivos.apispringbootcunoc.controller.dto.AdminCreateUserRequest;
import com.archivos.apispringbootcunoc.controller.dto.AdminUpdateUserRequest;
import com.archivos.apispringbootcunoc.controller.dto.AuthCreateUserRequest;
import com.archivos.apispringbootcunoc.persistence.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void getReports() {
        System.out.println("Getting reports");
    }

    public void createUser(AdminCreateUserRequest request, String sucursal) {
        System.out.println("Creating user");
        adminRepository.createUser(request.name(), request.username(), request.email(), request.password(), request.nit(), sucursal, request.role());
    }

    public void updateUser(AdminUpdateUserRequest request, String sucursal) {
        System.out.println("Updating user");
        adminRepository.updateUser(request.id(), request.name(), request.email(), request.nit(), request.username(), request.update(),sucursal);
    }

    public void updateUserTrue(String id, String sucursal) {
        System.out.println("Updating user");
        adminRepository.updateUser(Integer.parseInt(id), null, null, null, null, true,sucursal);
    }

    public void updateTargets() {
        System.out.println("Updating Targets");
    }

    public void deleteUser() {
        System.out.println("Deleting user");
    }
}
