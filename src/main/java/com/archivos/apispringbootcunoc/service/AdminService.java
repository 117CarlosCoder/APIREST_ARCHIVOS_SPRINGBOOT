package com.archivos.apispringbootcunoc.service;

import com.archivos.apispringbootcunoc.controller.dto.AdminCreateUserRequest;
import com.archivos.apispringbootcunoc.controller.dto.AdminUpdateUserRequest;
import com.archivos.apispringbootcunoc.controller.dto.AuthCreateUserRequest;
import com.archivos.apispringbootcunoc.persistence.entity.UsersAdminEntity;
import com.archivos.apispringbootcunoc.persistence.repository.AdminRepository;
import com.archivos.apispringbootcunoc.persistence.repository.UsersAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UsersAdminRepository usersAdminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void getReports() {
        System.out.println("Getting reports");
    }

    public void createUser(AdminCreateUserRequest request, String sucursal) {
        System.out.println("Creating user");
        String password ="";
        if (!request.password().isEmpty()){
         password= passwordEncoder.encode(request.password());
        }
        adminRepository.createUser(request.name(), request.username(), request.email(),password, request.nit(), sucursal, request.role());
    }

    public List<UsersAdminEntity> findUser(String valor, String sucursal) {
        System.out.println("Find user");
        return usersAdminRepository.findUser(valor, sucursal);

    }

    public void updateUser(AdminUpdateUserRequest request, String sucursal) {
        System.out.println("Updating user");
        System.out.println("Request " + request.toString());
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
