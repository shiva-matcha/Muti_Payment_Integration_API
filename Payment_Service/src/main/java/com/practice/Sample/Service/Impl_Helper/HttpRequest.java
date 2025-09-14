package com.practice.Sample.Service.Impl_Helper;

import com.practice.Sample.Const.Constants;
import com.practice.Sample.Exceptions.StripProviderException;
import com.practice.Sample.Http.HttpRequestPOJO;
import com.practice.Sample.Http.PspRequest;
import com.practice.Sample.Model.Lineitems;
import com.practice.Sample.Model.Requestbody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@Component
@Slf4j
@RequiredArgsConstructor
public class HttpRequest {

    private final PspRequest pspRequest;

    @Value("${strip_id}")
    public String userid;
    public HttpRequestPOJO prepareHttpRequest(Requestbody requestbody) {

        HttpHeaders headers = new HttpHeaders();

        headers.setBasicAuth(userid,"");
        headers.set("Content-Type","application/x-www-form-urlencoded");

        log.info("Basic Auth header is created");

//        class Myclass implements Consumer<HttpHeaders>{
//
//            private HttpHeaders headers;
//
//            public Myclass(HttpHeaders headers) {
//                this.headers= headers;
//            }
//
//            @Override
//            public void accept(HttpHeaders h) {
//                h.addAll(this.headers);
//            }
//        }

        // build form parameters as Stripe expects
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(Constants.SUCCESS_URL, Constants.URL);
        formData.add(Constants.CANCEL_URL, Constants.URL1);
        formData.add(Constants.MODE, Constants.PAYMENT);


        int index = 0;
        for (Lineitems item : requestbody.getLine_items()) {
            if(item.getQuantity()<=0){
                throw new StripProviderException(
                        "40001",
                        "The Quantity should be 1 or more",
                        HttpStatus.BAD_REQUEST
                );
            }
            formData.add(String.format("line_items[%d][price_data][currency]",index),item.getCurrency());
            formData.add(String.format("line_items[%d][price_data][product_data][name]",index), item.getProductName());
            formData.add(String.format("line_items[%d][price_data][unit_amount]",index), String.valueOf(item.getUnitAmount()));
            formData.add(String.format("line_items[%d][quantity]",index), String.valueOf(item.getQuantity()));
            index++;
        }




        HttpRequestPOJO httprequest = new HttpRequestPOJO();
        httprequest.setUri(Constants.CHECKOUT_SESSIONS_URI);
        httprequest.setMethod(HttpMethod.POST);
        httprequest.setHeaders(headers);
        httprequest.setBody(formData);



        return httprequest;
    }
}
