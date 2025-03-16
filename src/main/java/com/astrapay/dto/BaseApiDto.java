package com.astrapay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseApiDto<T> {
    private String status;
    private List<String> message = new ArrayList<>();
    private T data;

    public BaseApiDto(T data) {
        this.data = data;
        this.status = "OK";
    }

    public BaseApiDto(T data, String message) {
        this.data = data;
        this.status = "OK";
        this.message.add(message);
    }

    public BaseApiDto(String status, List<String> message) {
        this.status = status;
        this.message = message;
    }
}