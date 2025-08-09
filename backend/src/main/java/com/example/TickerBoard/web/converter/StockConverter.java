package com.example.TickerBoard.web.converter;

import com.example.TickerBoard.domain.Stock;

import static com.example.TickerBoard.web.dto.StockDTO.*;

public class StockConverter {

    public static StocksResponseDTO toStocksResponseDTO(Stock stock) {

        return StocksResponseDTO.builder()
                .name(stock.getName())
                .ticker(stock.getTicker())
                .build();
    };
}
