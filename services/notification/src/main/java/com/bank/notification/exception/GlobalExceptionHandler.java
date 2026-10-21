package com.bank.notification.exception;

import com.bank.notification.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotificationSendException.class)
    public ResponseEntity<ErrorResponseDto> handleNotificationSendException(Exception ex, WebRequest request){
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY)
                .body(new ErrorResponseDto(request.getDescription(false), HttpStatus.FAILED_DEPENDENCY, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobal(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto(request.getDescription(false),
                        HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now()));
    }
}
