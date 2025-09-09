package com.practice.Sample.Service.Impl;

import com.practice.Sample.Http.HttpRequest;
import com.practice.Sample.Http.PspRequest;
import com.practice.Sample.Model.Lineitems;
import com.practice.Sample.Model.Requestbody;
import com.practice.Sample.Service.Interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PspRequest pspRequest;

//    public PaymentServiceImpl(PspRequest pspRequest) {
//        this.pspRequest= pspRequest;
//
//    }

    @Override
    public String createpayment(Requestbody model) {

        HttpHeaders headers = new HttpHeaders();

        headers.setBasicAuth("sk_test_51RuuCUKGCGiVq2baDedcx58ztvCjRA1hBJl" +
                "PMCZ93rz4a0L1jqsd4IdhTyLa9W8KAFuBGFoRrdNxRfkgWCqjV6rd00ZZrFbOsU","");
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
        formData.add("success_url", "https://example.com/success");
        formData.add("cancel_url", "https://example.com/cancel");
        formData.add("mode", "payment");


        int index = 0;
        for (Lineitems item : model.getLine_items()) {
            formData.add("line_items[" + index + "][price_data][currency]", item.getCurrency());
            formData.add("line_items[" + index + "][price_data][product_data][name]", item.getProductName());
            formData.add("line_items[" + index + "][price_data][unit_amount]", String.valueOf(item.getUnitAmount()));
            formData.add("line_items[" + index + "][quantity]", String.valueOf(item.getQuantity()));
            index++;
        }

        // Stripe expects line_items as array style form-data
//        formData.add("line_items[0][price_data][currency]", "usd");
//        formData.add("line_items[0][price_data][product_data][name]", "Test Product");
//        formData.add("line_items[0][price_data][unit_amount]", "2000"); // in cents
//        formData.add("line_items[0][quantity]", "1");

        HttpRequest httprequest = new HttpRequest();
        httprequest.setUri("https://api.stripe.com/v1/checkout/sessions");
        httprequest.setMethod(HttpMethod.POST);
        httprequest.setHeaders(headers);
        httprequest.setBody(formData);

        pspRequest.setRequest(httprequest);

        log.info("Create payment request from payment service Impl");
        return pspRequest.makeHttpCall();
    }


}