package com.example.TickerBoard.web.response.code;

import com.example.TickerBoard.web.response.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseCode {

    _OK(HttpStatus.OK, "STOCK2001", "전체 종목 리스트 조회 성공"),

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
