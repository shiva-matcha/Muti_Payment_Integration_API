package com.practice.Sample.Exceptions;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class StripProviderException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    public StripProviderException(String errorCode, String errorMessage, HttpStatus httpStatus ) {
        super(errorMessage);
        this.errorCode= errorCode;
        this.errorMessage= errorMessage;
        this.httpStatus= httpStatus;
    }
}
