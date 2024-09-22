package com.archivos.apispringbootcunoc;

import com.archivos.apispringbootcunoc.persistence.entity.PermissionEntity;
import com.archivos.apispringbootcunoc.persistence.entity.RoleEntity;
import com.archivos.apispringbootcunoc.persistence.entity.RoleEnum;
import com.archivos.apispringbootcunoc.persistence.entity.UserEntity;
import com.archivos.apispringbootcunoc.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ApiSpringBootCunocApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSpringBootCunocApplication.class, args);
    }
    /*@Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();

            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity roleCashier = RoleEntity.builder()
                    .roleEnum(RoleEnum.CASHIER)
                    .permissionList(Set.of(createPermission, readPermission))
                    .build();

            RoleEntity roleClient = RoleEntity.builder()
                    .roleEnum(RoleEnum.CLIENT)
                    .permissionList(Set.of(readPermission))
                    .build();

            RoleEntity roleInventory = RoleEntity.builder()
                    .roleEnum(RoleEnum.INVENTORY)
                    .permissionList(Set.of(createPermission, readPermission))
                    .build();

            RoleEntity roleWarehouse = RoleEntity.builder()
                    .roleEnum(RoleEnum.WAREHOUSE)
                    .permissionList(Set.of(createPermission, readPermission))
                    .build();

            UserEntity userSantiago = UserEntity.builder()
                    .name("Santiago Marquez")
                    .username("santiago")
                    .nit(BigInteger.valueOf(12345678))
                    .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                    .email("santiago@example.com")
                    .sucursal("Sucursal 1")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleAdmin))
                    .update(false)
                    .cajero(0)
                    .build();

            UserEntity userDaniel = UserEntity.builder()
                    .name("Daniel Ruiz")
                    .username("daniel")
                    .nit(BigInteger.valueOf(12345677))
                    .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                    .email("daniel@example.com")
                    .sucursal("Sucursal 1")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleCashier))
                    .update(false)
                    .cajero(0)
                    .build();

            UserEntity userAndrea = UserEntity.builder()
                    .name("Andrea Suarez")
                    .username("andrea")
                    .nit(BigInteger.valueOf(12345676))
                    .password("")
                    .email("")
                    .sucursal("Sucursal 1")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleClient))
                    .update(false)
                    .cajero(0)
                    .build();

            UserEntity userAnyi = UserEntity.builder()
                    .name("Anyi Suc")
                    .username("anyi")
                    .nit(BigInteger.valueOf(12345675))
                    .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                    .email("anyi@example.com")
                    .sucursal("Sucursal 1")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleWarehouse))
                    .update(false)
                    .cajero(0)
                    .build();

            UserEntity userMario = UserEntity.builder()
                    .username("Mario Hernandez")
                    .username("mario")
                    .nit(BigInteger.valueOf(12345674))
                    .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                    .email("mario@example.com")
                    .sucursal("Sucursal 1")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleInventory))
                    .update(false)
                    .cajero(0)
                    .build();

            userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea, userAnyi, userMario));
        };
    }*/
}
