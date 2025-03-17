package br.com.ballon.domain.exception;


import java.time.LocalDateTime;

public class BallonException extends RuntimeException{
    private LocalDateTime date;

    public BallonException(String message) {
        super(message);
        this.date = LocalDateTime.now();
    }
}
