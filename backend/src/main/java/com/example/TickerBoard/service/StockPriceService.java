package com.example.TickerBoard.service;

import com.example.TickerBoard.domain.StockPrice;

import java.util.List;

public interface StockPriceService {

    public List<StockPrice> getStockPriceList(String ticker);
}
