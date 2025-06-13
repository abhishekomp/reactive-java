package org.aom._01_demos._01_exceptionScenarios;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class _01_ErrorHandlingDemo {
    public Flux<String> createErrorThenValueFlux() {
        return Flux.just("A", "B")
                .delayElements(Duration.ofSeconds(1))
                .concatWith(Flux.error(new RuntimeException("Boom!")))
                .concatWith(Flux.just("C")); // This will never be emitted!
    }

    public static void main(String[] args) {
        _01_ErrorHandlingDemo main = new _01_ErrorHandlingDemo();
        main.createErrorThenValueFlux()
                .subscribe(
                        item -> System.out.println("Received: " + item),
                        error -> System.err.println("Error signal sent to subscriber: " + error.getMessage()),
                        () -> System.out.println("Completed!")
                );
        // Keep the application running to see the output
        try {
            Thread.sleep(5000); // Sleep for 5 seconds to allow all emissions to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}