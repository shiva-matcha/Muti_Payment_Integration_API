package com.practice.Sample.Http;

import com.practice.Sample.Model.ResponsBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class PspRequest {

    // java intermidiate object
    ResponsBody responsbody;

    // lets create Bean using constructor dependency
    private HttpRequestPOJO httpRequestPOJO;
    private RestClient restClient;
//    @Autowired
    public PspRequest(RestClient.Builder restBuilder) {
        this.restClient = restBuilder.build();

    }

//    public void setRequest(HttpRequestPOJO httpRequestPOJO) {
//        this.httpRequestPOJO = httpRequestPOJO;
//    }

    public String makeHttpCall(HttpRequestPOJO httpRequestPOJO) {
        String response;

        ResponseEntity<String> responsJSON= restClient.method(httpRequestPOJO.getMethod())
                .uri(httpRequestPOJO.getUri())
                .headers((HttpHeaders t)-> t.addAll(httpRequestPOJO.getHeaders()))
                .body(httpRequestPOJO.getBody())
                .retrieve()
                .toEntity(String.class);

        response=responsJSON.getBody();


        return response;
    }
}