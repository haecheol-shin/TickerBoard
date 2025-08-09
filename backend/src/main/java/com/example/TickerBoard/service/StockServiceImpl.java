package com.example.TickerBoard.service;

import com.example.TickerBoard.domain.Stock;
import com.example.TickerBoard.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public List<Stock> getStockList() {

        return stockRepository.findAll();
    }
}
