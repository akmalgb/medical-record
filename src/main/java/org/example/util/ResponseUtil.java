package org.example.util;

import org.example.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {
    public static <T> ResponseEntity<ResponseDto<T>> success(int statusCode, String message, T result) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(statusCode);
        response.setSuccess(true);
        response.setMessage(message);
        response.setResult(result);
        return ResponseEntity.status(statusCode).body(response);
    }

    public static <T> ResponseEntity<ResponseDto<T>> notFound() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setSuccess(false);
        response.setMessage("data not found");
        response.setResult(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
    }

    public static <T> ResponseEntity<ResponseDto<T>> internalServerError() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("internal server error");
        response.setResult(null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
    }
}
