package com.example.TickerBoard.service;

import com.example.TickerBoard.domain.Stock;
import com.example.TickerBoard.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public List<Stock> getStockList() {

        return stockRepository.findAll();
    }

    @Override
    public Optional<Stock> getStockByTicker(String ticker) {
        return stockRepository.findByTicker(ticker);
    }
}
