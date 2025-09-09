package com.practice.Sample.Http;

import com.practice.Sample.Model.Requestbody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;

@Slf4j
@Component
public class PspRequest {

    // lets create Bean using constructor dependency

    private HttpRequest httprequest;
    private RestClient restClient;
//    @Autowired
    public PspRequest(RestClient.Builder restBuilder) {
        this.restClient = restBuilder.build();

    }

    public void setRequest(HttpRequest httprequest) {
        this.httprequest = httprequest;
    }

    public String makeHttpCall() {


        ResponseEntity<String> responss= restClient.method(httprequest.getMethod())
                .uri(httprequest.getUri())
                .headers((HttpHeaders t)-> t.addAll(httprequest.getHeaders()))
                .body(httprequest.getBody())
                .retrieve()
                .toEntity(String.class);

        return responss.getBody();
    }
}