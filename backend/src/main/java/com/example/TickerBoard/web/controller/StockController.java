package com.example.TickerBoard.web.controller;

import com.example.TickerBoard.domain.Stock;
import com.example.TickerBoard.service.StockService;
import com.example.TickerBoard.web.converter.StockConverter;
import com.example.TickerBoard.web.dto.StocksResponseWrapper;
import com.example.TickerBoard.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

import static com.example.TickerBoard.web.dto.StockDTO.*;
import static com.example.TickerBoard.web.response.code.FailureCode.STOCK_LIST_NOT_FOUND;
import static com.example.TickerBoard.web.response.code.SuccessCode.STOCK_FOUND;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/stocks")
    public ApiResponse<List<StocksResponseDTO>> getAllStocks() {
        StocksResponseWrapper wrapper = stockService.getStockList();
        List<StocksResponseDTO> dtoList = wrapper.getStocks();

        return dtoList.isEmpty()
                ? ApiResponse.onFailure(dtoList, STOCK_LIST_NOT_FOUND)
                : ApiResponse.onSuccess(dtoList, STOCK_FOUND);
    }

}
