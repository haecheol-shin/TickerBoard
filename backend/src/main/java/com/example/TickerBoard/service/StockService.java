package com.example.TickerBoard.service;

import com.example.TickerBoard.domain.Stock;
import com.example.TickerBoard.web.dto.StockDTO;
import com.example.TickerBoard.web.dto.StocksResponseWrapper;

import java.util.List;
import java.util.Optional;

import static com.example.TickerBoard.web.dto.StockDTO.*;

public interface StockService {

    public StocksResponseWrapper getStockList();
    public Optional<Stock> getStockByTicker(String ticker);
}
