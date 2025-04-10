package com.adityapdev.ChaChing_api.repository;

import com.adityapdev.ChaChing_api.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Long> {
}
