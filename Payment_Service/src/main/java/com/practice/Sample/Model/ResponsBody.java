package com.practice.Sample.Model;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Data
public class ResponsBody {
    private String id;
    private String url;
}