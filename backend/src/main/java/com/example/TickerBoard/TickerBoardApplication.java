package com.example.TickerBoard;

import com.example.TickerBoard.global.excel.ExcelDataImporter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class TickerBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TickerBoardApplication.class, args);
	}

//	@Bean
//	CommandLineRunner importData(ExcelDataImporter importer) {
//		return args -> {
//			importer.importFromExcel("src/main/resources/StockPrices.xlsx");
//		};
//	}

}
