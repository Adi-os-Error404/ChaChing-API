package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.dto.arbitrage.ArbitrageDto;

import java.math.BigDecimal;
import java.util.List;

public interface IArbitrageService {
    List<ArbitrageDto> detectArbitrage(BigDecimal profitMargin, List<String> coinSymbols);
}
