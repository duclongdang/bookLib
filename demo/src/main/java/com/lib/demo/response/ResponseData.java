package com.lib.demo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseData<Object> {
    private Object data;
    private String message;
    private int statusCode;

    public ResponseData(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ResponseData(Object data, String message, int statusCode) {
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }
}
