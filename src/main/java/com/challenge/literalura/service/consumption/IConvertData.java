package com.challenge.literalura.service.consumption;

public interface IConvertData {
    <T> T getData(String json, Class<T> typeClass);
}
