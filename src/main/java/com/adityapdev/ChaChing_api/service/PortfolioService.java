package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.portfolio.CoinInPortfolioDto;
import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.entity.User;
import com.adityapdev.ChaChing_api.entity.UserCoin;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.mapper.PortfolioMapper;
import com.adityapdev.ChaChing_api.repository.CoinRepository;
import com.adityapdev.ChaChing_api.repository.UserCoinRepository;
import com.adityapdev.ChaChing_api.repository.UserRepository;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinService;
import com.adityapdev.ChaChing_api.service.interfaces.IPortfolioService;
import com.adityapdev.ChaChing_api.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioService implements IPortfolioService {

    private final UserCoinRepository userCoinRepository;
    private final IUserService userService;
    private final ICoinService coinService;

    public PortfolioService(UserCoinRepository userCoinRepository, UserRepository userRepository, CoinRepository coinRepository) {
        this.userCoinRepository = userCoinRepository;
        this.userService = new UserService(userRepository);
        this.coinService = new CoinService(coinRepository);
    }

    @Override
    public List<CoinInPortfolioDto> getCurrentUserCoins() {
        List<Coin> userCoins = userCoinRepository.findCoinsByUserId(userService.getCurrentUser().getId());
        return userCoins.stream().map(PortfolioMapper::mapToPortfolioDto).collect(Collectors.toList());
    }

    @Override
    public CoinInPortfolioDto addCoinToUserPort(String coinId) {
        User user = userService.getCurrentUser();
        Coin coin = coinService.findCoinById(coinId);
        UserCoin userCoin = new UserCoin(user, coin);
        userCoinRepository.save(userCoin);
        return PortfolioMapper.mapToPortfolioDto(coin);
    }

    @Override
    public String removeCoinFromUserPort(String coinId) {
        User user = userService.getCurrentUser();
        Coin coin = coinService.findCoinById(coinId);
        UserCoin userCoin = userCoinRepository.findByUserIdAndCoinId(user.getId(), coin.getId());
        if (userCoin == null)
            throw new ResourceNotFoundException(coinId + " is not in portfolio.");
        userCoinRepository.deleteById(userCoin.getId());
        return coin.getName() + " removed from portfolio successfully.";
    }


}
