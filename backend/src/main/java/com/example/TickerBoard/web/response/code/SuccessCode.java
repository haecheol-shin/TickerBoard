package com.example.TickerBoard.web.response.code;

import com.example.TickerBoard.web.response.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseCode {

    // 종목 관련 코드
    STOCK_FOUND(HttpStatus.OK, "STOCK2001", "전체 종목 리스트 조회 성공"),

    // 가격 관련 코드
    PRICE_FOUND(HttpStatus.OK, "PRICE2001", "주식 가격 리스트 조회 성공"),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ResponseDTO response() {
        return com.example.TickerBoard.web.response.ResponseDTO.builder()
                .code(code)
                .message(message)
                .status(httpStatus)
                .build();
    }
}
