package com.challenge.literalura.service.consumption;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public <T> T getData(String json, Class<T> typeClass) {
        try {
            return objectMapper.readValue(json, typeClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
