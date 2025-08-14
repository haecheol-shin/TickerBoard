package com.example.TickerBoard.web.converter;

import com.example.TickerBoard.domain.StockPrice;

import java.util.List;

import static com.example.TickerBoard.web.dto.StockPriceDTO.*;

public class StockPriceConverter {

    public static StockPricesResponseDTO toStockPricesResponseDTO(String ticker, String name, List<ClosePriceDTO> closePriceDTOList) {

        return StockPricesResponseDTO.builder()
                .name(name)
                .ticker(ticker)
                .closePriceList(closePriceDTOList)
                .build();

    }

    public static ClosePriceDTO toClosePriceDTOList(StockPrice stockPrice) {

        return ClosePriceDTO.builder()
                .date(stockPrice.getDate())
                .closePrice(stockPrice.getClose())
                .build();
    }

}
