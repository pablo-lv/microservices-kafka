package com.plucas.ws.products.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorMessage {
    private LocalDateTime timestamp;

    private String message;

    private String Details;

    public ErrorMessage(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        Details = details;
    }
}
