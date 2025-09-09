package com.practice.Sample.Model;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Data
public class Requestbody {
    private String success_Url= "https://example.com/success";
    private String cancel_Url= "https://example.com/cancel";
    private List<Lineitems> line_items;
}
