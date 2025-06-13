package org.aom._01_demos._01_exceptionScenarios;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class _02_ErrorHandling_onErrorResume_Demo {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B", "C").delayElements(Duration.ofSeconds(1))
            .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
            .concatWith(Flux.just("X")) // Will NOT be reached unless we handle error
            .onErrorResume(e -> {
                System.out.println("Error intercepted: " + e.getMessage());
                return Flux.just("D", "E").delayElements(Duration.ofSeconds(1));
            });

        flux.subscribe(
            item -> System.out.println("Received: " + item),
            error -> System.err.println("Error signal sent to subscriber: " + error),
            () -> System.out.println("Completed!")
        );

        // Keep the application running to see the output
        try {
            Thread.sleep(10000); // Sleep for 10 seconds to allow all emissions to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}