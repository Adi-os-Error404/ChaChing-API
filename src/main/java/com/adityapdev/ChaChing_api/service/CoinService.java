package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.coin.CoinAllDetailsDto;
import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.mapper.CoinMapper;
import com.adityapdev.ChaChing_api.repository.CoinRepository;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinGeckoService;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoinService implements ICoinService {

    private final CoinRepository coinRepository;
    private final ICoinGeckoService coinGeckoService = new CoinGeckoService();

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @Override
    public List<CoinAllDetailsDto> getAllCoins() {
        List<Coin> coins = coinRepository.findAll();
        return coins.stream().map(CoinMapper::mapToCoinDto).collect(Collectors.toList());
    }

    @Override
    public CoinAllDetailsDto getCoinById(String coinId) {
        return CoinMapper.mapToCoinDto(findCoinById(coinId));
    }

    @Override
    public void deleteCoin(String coinId) {
        Coin coin = coinRepository.findByCoinId(coinId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Coin \"%s\" is not found.", coinId)));
        coinRepository.deleteById(coin.getId());
    }

    @Override
    public Coin findCoinById(String coinId) {
        Optional<Coin> coinInDb = coinRepository.findByCoinId(coinId);
        if (coinInDb.isEmpty()) {
            Coin newCoin = coinGeckoService.getCoinByCoinId(coinId);
            return coinRepository.save(newCoin);
        }
        return coinInDb.get();
    }

}
