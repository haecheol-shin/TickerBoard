package com.example.TickerBoard.service;

import com.example.TickerBoard.domain.StockPrice;
import com.example.TickerBoard.repository.StockPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockPriceServiceImpl implements StockPriceService {

    private final StockPriceRepository stockPriceRepository;

    @Override
    public List<StockPrice> getStockPriceList(String ticker) {
        List<StockPrice> stockPriceList = stockPriceRepository.findByStock_Ticker(ticker);
        return stockPriceList;
    }
}
