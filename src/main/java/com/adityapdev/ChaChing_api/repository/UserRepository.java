package com.adityapdev.ChaChing_api.repository;

import com.adityapdev.ChaChing_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
