package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.coin.AddCoinDto;
import com.adityapdev.ChaChing_api.dto.coin.CoinCommentDetailDto;
import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.exception.ConflictException;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.mapper.CoinMapper;
import com.adityapdev.ChaChing_api.repository.CoinRepository;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinGeckoService;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public CoinCommentDetailDto addCoin(AddCoinDto addCoinDto) {
        if (coinRepository.findByCoinId(addCoinDto.getCoinId()).isPresent())
            throw new ConflictException(String.format("Coin \"%s\" is already exists.", addCoinDto.getCoinId()));
        Coin coin = CoinMapper.mapToCoin(addCoinDto);
        Coin savedCoin = coinRepository.save(coin);
        return CoinMapper.mapToCoinDto(savedCoin);
    }

    @Override
    public List<CoinCommentDetailDto> getAllCoins() {
        List<Coin> coins = coinRepository.findAll();
        return coins.stream().map(CoinMapper::mapToCoinDto).collect(Collectors.toList());
    }

    @Override
    public CoinCommentDetailDto getCoinById(String coinId) {
        return CoinMapper.mapToCoinDto(findCoinById(coinId));
    }

    @Override
    public CoinCommentDetailDto updateCurrentPrice(String coinId, BigDecimal currentPriceUsd) {
        Coin coin = findCoinById(coinId);
        coin.setCurrentPrice(currentPriceUsd);
        Coin savedCoin = coinRepository.save(coin);
        return CoinMapper.mapToCoinDto(savedCoin);
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
