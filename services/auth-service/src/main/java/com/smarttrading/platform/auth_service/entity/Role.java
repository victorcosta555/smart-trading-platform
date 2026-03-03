package com.smarttrading.platform.auth_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    private UUID id;
    private String name;
}
