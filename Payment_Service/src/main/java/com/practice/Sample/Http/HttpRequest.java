package com.practice.Sample.Http;


import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Data
public class HttpRequest {

    private String uri;
    private HttpMethod method;
    private HttpHeaders headers;
    private Object body;
}
