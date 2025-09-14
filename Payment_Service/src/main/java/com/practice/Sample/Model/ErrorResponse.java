package com.practice.Sample.Model;

public class ErrorResponse {
    private String erroCode;
    private String erroMessage;

    public ErrorResponse(String erroCode, String erroMessage) {
        this.erroCode = erroCode;
        this.erroMessage = erroMessage;
    }
}
