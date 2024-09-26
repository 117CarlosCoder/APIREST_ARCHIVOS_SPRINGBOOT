package com.archivos.apispringbootcunoc.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class UsersAdminEntity {
    @Id
    private Long id;
    private String name;//
    private String email;//
    private Long nit;//
    private String password;//
    private String username;//
    private String update;
}
