package com.example.TickerBoard.web.response.code;

import com.example.TickerBoard.web.response.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum FailureCode implements BaseCode {

    // 종목 관련 코드
    STOCK_LIST_NOT_FOUND(HttpStatus.BAD_REQUEST, "STOCK4001", "전체 종목 리스트 조회 실패"),
    STOCK_NOT_FOUND(HttpStatus.BAD_REQUEST, "STOCK4002", "티커 조회 실패"),

    // 가격 관련 코드
    PRICE_NOT_FOUND(HttpStatus.BAD_REQUEST, "PRICE4001", "가격 조회 실패"),

    ;

    private HttpStatus httpStatus;
    private String code;
    private String message;

    @Override
    public ResponseDTO response() {
        return ResponseDTO.builder()
                .code(code)
                .message(message)
                .status(httpStatus)
                .build();
    }
}
