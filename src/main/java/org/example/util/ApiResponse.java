package org.example.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {

    private String status;
    private T data;
    private ApiError error;

    public ApiResponse(String status, T data, ApiError error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", data, null);
    }

    public static <T> ApiResponse<T> error(String message, int statusCode) {
        return new ApiResponse<>("error", null, new ApiError(message, statusCode));
    }

}
