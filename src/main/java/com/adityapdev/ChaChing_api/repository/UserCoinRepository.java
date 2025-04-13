package com.adityapdev.ChaChing_api.repository;

import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.entity.UserCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCoinRepository extends JpaRepository<UserCoin, Long> {

    @Query("SELECT uc.coin FROM UserCoin uc WHERE uc.user.id = :userId")
    List<Coin> findCoinsByUserId(@Param("userId") Long userId);

    @Query("SELECT uc FROM UserCoin uc WHERE uc.user.id = :userId AND uc.coin.id = :coinId")
    UserCoin findByUserIdAndCoinId(@Param("userId") Long userId, @Param("coinId") Long coinId);

}
