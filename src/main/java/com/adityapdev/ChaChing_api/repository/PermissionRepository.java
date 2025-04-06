package com.adityapdev.ChaChing_api.repository;

import com.adityapdev.ChaChing_api.config.PermissionType;
import com.adityapdev.ChaChing_api.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Optional<Permission> findByPermissionType(PermissionType permissionType);
}
