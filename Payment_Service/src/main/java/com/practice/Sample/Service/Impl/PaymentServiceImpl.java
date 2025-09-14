package com.practice.Sample.Service.Impl;

import com.practice.Sample.Http.HttpRequestPOJO;
import com.practice.Sample.Http.PspRequest;
import com.practice.Sample.Model.Requestbody;
import com.practice.Sample.Model.ResponsBody;
import com.practice.Sample.Service.Impl_Helper.HttpRequest;
import com.practice.Sample.Service.Interfaces.PaymentService;
import com.practice.Sample.Util.Convertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Value("${strip_id}")
    private String userid;

    private final PspRequest pspRequest;

    private final Convertor convertor;

    private final HttpRequest httpRequest;


//    public PaymentServiceImpl(PspRequest pspRequest) {
//        this.pspRequest= pspRequest;
//
//    }

    @Override
    public String createpayment(Requestbody model) {

        HttpRequestPOJO request = httpRequest.prepareHttpRequest(model);



        log.info("Create payment request from payment service Impl");

        ResponsBody responsBody =convertor.convert(pspRequest.makeHttpCall(request), ResponsBody.class);

        return responsBody.toString();
    }


}