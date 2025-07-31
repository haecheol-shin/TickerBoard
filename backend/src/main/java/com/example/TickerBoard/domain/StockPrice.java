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

    private Long open;
    private Long high;
    private Long low;
    private Long close;
    private Long volume;

    @Column(name = "change_rate", precision = 6, scale = 4)
    private BigDecimal changeRate;

}
