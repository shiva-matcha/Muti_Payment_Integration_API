package com.practice.Sample.Controller;


import com.practice.Sample.Const.Constants;
import com.practice.Sample.Http.PspRequest;
import com.practice.Sample.Model.Requestbody;
import com.practice.Sample.Model.ResponsBody;
import com.practice.Sample.Service.Impl.PaymentServiceImpl;
import com.practice.Sample.Service.Interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.script.ScriptTemplateConfig;

@Slf4j
@RestController
@RequestMapping(Constants.CONSTANT)
@RequiredArgsConstructor
public class PaymentsController {

    private final PaymentService paymentservice;
    private final ResponsBody responsbody;
    private final PspRequest psprequest;



    @PostMapping
    public String method1(@RequestBody (required = false) Requestbody model){

        //String result= psprequest.makeHttpCall();
        //log.info("The response is going to print",result);


        return paymentservice.createpayment(model);
    }
}
