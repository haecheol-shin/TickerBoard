package com.example.TickerBoard.web.dto;

import com.example.TickerBoard.domain.StockPrice;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class StockPriceDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StockPricesResponseDTO {

        private String name;
        private String ticker;
        private List<ClosePriceDTO> closePriceList;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClosePriceDTO {
        private LocalDate date;
        private int closePrice;
    }
}
