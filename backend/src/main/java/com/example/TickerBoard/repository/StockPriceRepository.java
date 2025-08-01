package com.example.TickerBoard.repository;


import com.example.TickerBoard.domain.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StockPriceRepository extends JpaRepository<StockPrice, String> {

    List<StockPrice> findByStock_Ticker(String ticker);
}
