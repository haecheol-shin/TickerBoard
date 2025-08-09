package com.example.TickerBoard.web.response;

import com.example.TickerBoard.web.response.code.BaseCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "time", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty
    private boolean isSuccess;

    @JsonProperty
    private LocalDateTime time;

    @JsonProperty
    private String code;

    @JsonProperty
    private String message;

    @JsonProperty
    private T result;

    // 성공 응답
    public static <T> ApiResponse<T> onSuccess(T result, BaseCode status) {
        return new ApiResponse<>(true, LocalDateTime.now(), status.response().getCode(), status.response().getMessage(), result);
    }

    // 실패 응답
    public static <T> ApiResponse<T> onFailure(T result, BaseCode status) {
        return new ApiResponse<>(false, LocalDateTime.now(), status.response().getCode(), status.response().getMessage(), result);
    }


}
