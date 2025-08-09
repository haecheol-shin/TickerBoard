package com.example.TickerBoard.repository;

import com.example.TickerBoard.domain.Stock;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, String> {

    @EntityGraph(attributePaths = "prices")
    List<Stock> findAll();

    Optional<Stock> findByTicker(String ticker);
}
