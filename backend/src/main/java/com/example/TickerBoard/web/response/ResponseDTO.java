package com.example.TickerBoard.web.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseDTO {

    private HttpStatus status;
    private String code;
    private String message;

}
