package com.example.cafeservice.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Result {
    private String message;
    private boolean status;
    private Object data;
    private String exception;

    public Result(String message, boolean success, Object data, Exception error) {
        this.message = message;
        this.status = success;
        this.data = data;
        this.exception = error.getMessage();
    }

    public Result(String message, boolean success, Object data) {
        this.message = message;
        this.status = success;
        this.data = data;
    }

    public Result(String message, boolean success) {
        this.message = message;
        this.status = success;
    }
}
