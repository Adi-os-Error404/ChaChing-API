package com.adityapdev.ChaChing_api.repository;

import com.adityapdev.ChaChing_api.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
