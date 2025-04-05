package com.adityapdev.ChaChing_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission_type", nullable = false, unique = true)
    private PermissionType permissionType;

    public Permission() {}

    public Permission(Integer id, PermissionType permissionType) {
        this.id = id;
        this.permissionType = permissionType;
    }

    public Integer getId() {
        return id;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }
}
