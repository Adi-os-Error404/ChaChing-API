package com.adityapdev.ChaChing_api.repository;

import com.adityapdev.ChaChing_api.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin, Long> {
    Optional<Coin> findByCoinId(String coinId);
}
