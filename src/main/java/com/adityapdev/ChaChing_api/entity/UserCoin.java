package com.adityapdev.ChaChing_api.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "user_coin",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "coin_id"})}
)
public class UserCoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "coin_id", nullable = false)
    private Coin coin;

    public UserCoin() {}

    public UserCoin(User user, Coin coin) {
        this.user = user;
        this.coin = coin;
    }

    public Long getId() {
        return id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

}
