package com.practice.Sample.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Convertor {
    private final ObjectMapper objectmapper;

    public <T> T convert(String content,Class<T> value)  {
        try{
            return objectmapper.readValue(content, value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
