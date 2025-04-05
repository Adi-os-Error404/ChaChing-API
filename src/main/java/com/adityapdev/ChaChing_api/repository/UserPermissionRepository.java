package com.adityapdev.ChaChing_api.repository;

import com.adityapdev.ChaChing_api.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
}
