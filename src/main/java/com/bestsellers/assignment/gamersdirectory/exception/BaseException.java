package com.bestsellers.assignment.gamersdirectory.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String code;

    public String getMessageKey() {
        return "error.message." + code;
    }

    public String getTitleKey() {
        return "error.title." + code;
    }
}
