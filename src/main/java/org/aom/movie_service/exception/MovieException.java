package org.aom.movie_service.exception;

public class MovieException extends RuntimeException {
    String message;
    public MovieException(String message) {
        super(message);
        this.message = message;
    }

    public MovieException(Throwable ex) {
        super(ex);
        this.message = ex.getMessage();
    }
}