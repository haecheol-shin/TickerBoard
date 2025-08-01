package com.example.TickerBoard.global.excel;

import com.example.TickerBoard.domain.Stock;
import com.example.TickerBoard.domain.StockPrice;
import com.example.TickerBoard.repository.StockPriceRepository;
import com.example.TickerBoard.repository.StockRepository;
import com.monitorjbl.xlsx.StreamingReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ExcelDataImporter {

    private final StockRepository stockRepository;
    private final StockPriceRepository stockPriceRepository;

    public void importFromExcel(String path) throws Exception {
        try (InputStream is = new FileInputStream(path);
             Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(100)
                     .bufferSize(4096)
                     .open(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean isHeader = true;

            for (Row row : sheet) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String ticker = row.getCell(7).getStringCellValue();
                String name = row.getCell(8).getStringCellValue();
                String market = row.getCell(9).getStringCellValue();

                Stock stock = stockRepository.findByTicker(ticker)
                        .orElseGet(() -> stockRepository.save(
                                Stock.builder()
                                        .ticker(ticker)
                                        .name(name)
                                        .market(Stock.Market.valueOf(market))
                                        .build()
                        ));

                LocalDate date = LocalDate.parse(row.getCell(0).getStringCellValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                StockPrice price = StockPrice.builder()
                        .stock(stock)
                        .date(date)
                        .open((int) row.getCell(1).getNumericCellValue())
                        .high((int) row.getCell(2).getNumericCellValue())
                        .low((int) row.getCell(3).getNumericCellValue())
                        .close((int) row.getCell(4).getNumericCellValue())
                        .volume((long) row.getCell(5).getNumericCellValue())
                        .changeRate(BigDecimal.valueOf(row.getCell(6).getNumericCellValue()))
                        .build();

                stockPriceRepository.save(price);
            }
        }
    }



}
