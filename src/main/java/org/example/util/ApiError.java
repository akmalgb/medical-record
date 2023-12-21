package org.example.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiError {
    private String message;
    private int statusCode;

    public ApiError(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
