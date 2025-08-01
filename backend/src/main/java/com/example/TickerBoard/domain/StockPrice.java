package com.example.TickerBoard.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_stock_prices")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StockPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticker", referencedColumnName = "ticker")
    private Stock stock;

    @Column(nullable = false)
    private LocalDate date;

    private int open;
    private int high;
    private int low;
    private int close;
    private Long volume;

    @Column(precision = 10, scale = 4)
    private BigDecimal changeRate;

}
