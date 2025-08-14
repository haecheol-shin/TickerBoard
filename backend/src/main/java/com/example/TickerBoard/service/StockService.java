package com.example.TickerBoard.service;

import com.example.TickerBoard.domain.Stock;
import java.util.List;
import java.util.Optional;

public interface StockService {

    public List<Stock> getStockList();
    public Optional<Stock> getStockByTicker(String ticker);
}
