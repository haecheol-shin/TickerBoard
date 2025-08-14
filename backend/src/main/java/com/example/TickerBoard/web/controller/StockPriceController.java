package com.example.TickerBoard.web.controller;

import com.example.TickerBoard.domain.Stock;
import com.example.TickerBoard.domain.StockPrice;
import com.example.TickerBoard.service.StockPriceService;
import com.example.TickerBoard.service.StockService;
import com.example.TickerBoard.web.converter.StockPriceConverter;
import com.example.TickerBoard.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.TickerBoard.web.dto.StockPriceDTO.*;
import static com.example.TickerBoard.web.response.code.FailureCode.*;
import static com.example.TickerBoard.web.response.code.SuccessCode.*;

@RestController
@RequiredArgsConstructor
public class StockPriceController {

    private final StockPriceService stockPriceService;
    private final StockService stockService;

    @GetMapping("/stocks/{ticker}")
    public ApiResponse<StockPricesResponseDTO> getStockPriceList (@PathVariable("ticker") String ticker) {

        // 티커로 주식 이름 찾기
        Optional<Stock> stock = stockService.getStockByTicker(ticker);
        if (stock.isEmpty()) {
            return ApiResponse.onFailure(null, STOCK_NOT_FOUND);
        }

        // 주식 가격 리스트 찾기
        List<StockPrice> stockPriceList = stockPriceService.getStockPriceList(ticker);
        if (stockPriceList.isEmpty()) {
            return ApiResponse.onFailure(null, PRICE_NOT_FOUND);
        }

        // 일자별 종가만 사용
        List<ClosePriceDTO> closePriceDTOList = new ArrayList<>();
        for (StockPrice stockPrice : stockPriceList) {
             closePriceDTOList.add(StockPriceConverter.toClosePriceDTOList(stockPrice));
        }

        // response 생성
        StockPricesResponseDTO response = StockPriceConverter.toStockPricesResponseDTO(ticker, stock.get().getName(), closePriceDTOList);

        return ApiResponse.onSuccess(response, PRICE_FOUND);

    }
}
