package com.adityapdev.ChaChing_api.config;

import com.adityapdev.ChaChing_api.entity.Permission;
import com.adityapdev.ChaChing_api.repository.PermissionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PermissionDataInitialiser implements CommandLineRunner {

    private final PermissionRepository permissionRepository;

    public PermissionDataInitialiser(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void run(String... args) {
        for (PermissionType type : PermissionType.values()) {
            permissionRepository.findByPermissionType(type)
                    .orElseGet(() -> {
                        Permission permission = new Permission(null, type);
                        return permissionRepository.save(permission);
                    });
        }
    }

}