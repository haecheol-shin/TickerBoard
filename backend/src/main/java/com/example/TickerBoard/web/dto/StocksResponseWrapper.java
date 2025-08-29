package com.example.TickerBoard.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StocksResponseWrapper {

    private List<StockDTO.StocksResponseDTO> stocks;

}
