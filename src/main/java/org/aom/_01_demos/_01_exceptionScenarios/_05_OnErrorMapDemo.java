package org.aom._01_demos._01_exceptionScenarios;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class _05_OnErrorMapDemo {
    public static void main(String[] args) {
        // Create a Flux that emits some values and then throws an error
        Flux<String> flux = new _05_OnErrorMapDemo().getFlux();

        // Subscribe to the Flux and handle the error
        flux.subscribe(
                item -> System.out.println("[onNext] Received: " + item),
                error -> {
                    System.out.println("[onError] Type: " + error.getClass());
                    System.out.println("[onError] Message: " + error.getMessage());
                    // If you want, also see the cause
                    if (error.getCause() != null) {
                        System.out.println("  Caused by: " + error.getCause().getClass() + " - " + error.getCause().getMessage());
                    }
                },
                () -> System.out.println("[onComplete] Done!")
        );
        // Keep the application running long enough to see the emissions
        System.out.println("Press a key to end");
        try {
            System.in.read(); // Wait for user input to end the application
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Flux<String> getFlux() {
        // Create a Flux that emits some values and then throws an error
        Flux<String> flux = Flux.just("A", "B", "X", "C")
                .delayElements(Duration.ofSeconds(1))
                .map(value -> {
                    if ("X".equals(value)) throw new IllegalArgumentException("X is not allowed!");
                    return value;
                })
                .onErrorMap(originalError ->
                        new CustomServiceException("Mapped to service error: " + originalError.getMessage(), originalError)
                );
        return flux;
    }

    // Custom exception for demo
    static class CustomServiceException extends RuntimeException {
        public CustomServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}