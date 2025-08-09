package com.example.TickerBoard.web.controller;

import com.example.TickerBoard.domain.Stock;
import com.example.TickerBoard.service.StockService;
import com.example.TickerBoard.web.converter.StockConverter;
import com.example.TickerBoard.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.example.TickerBoard.web.dto.StockDTO.*;
import static com.example.TickerBoard.web.response.code.FailureCode._FAIL;
import static com.example.TickerBoard.web.response.code.SuccessCode._OK;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ApiResponse<List<StocksResponseDTO>> getAllStocks() {
        List<Stock> stockList = stockService.getStockList();

        List<StocksResponseDTO> stocksResponseDTOList = new ArrayList<>();
        for (Stock stock : stockList) {
            stocksResponseDTOList.add(StockConverter.toStocksResponseDTO(stock));
        }

        stocksResponseDTOList.sort(Comparator.comparing(StocksResponseDTO::getName));

        if (stockList.isEmpty()) {
            return ApiResponse.onFailure(stocksResponseDTOList, _FAIL);
        } else {
            return ApiResponse.onSuccess(stocksResponseDTOList, _OK);
        }
    }

}
