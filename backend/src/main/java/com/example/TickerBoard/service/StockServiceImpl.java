package com.example.TickerBoard.service;

import com.example.TickerBoard.domain.Stock;
import com.example.TickerBoard.repository.StockRepository;
import com.example.TickerBoard.web.converter.StockConverter;
import com.example.TickerBoard.web.dto.StocksResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.example.TickerBoard.web.dto.StockDTO.*;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    @Cacheable(value = "stocks", key = "'all'")
    public StocksResponseWrapper getStockList() {
        System.out.println("DB 조회 발생"); // 캐시 적중 시 실행되지 않음

        List<Stock> stockList = stockRepository.findAll();

        List<StocksResponseDTO> dtoList =  stockList.stream()
                .map(StockConverter::toStocksResponseDTO)
                .sorted(Comparator.comparing(StocksResponseDTO::getName))
                .toList();

        return StocksResponseWrapper.builder()
                .stocks(dtoList)
                .build();
    }

    @Override
    public Optional<Stock> getStockByTicker(String ticker) {
        return stockRepository.findByTicker(ticker);
    }
}
