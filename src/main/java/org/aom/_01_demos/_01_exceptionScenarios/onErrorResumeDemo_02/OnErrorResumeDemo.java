package org.aom._01_demos._01_exceptionScenarios.onErrorResumeDemo_02;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class OnErrorResumeDemo {
    public static Flux<String> createAndHandleFlux() {
        return Flux.just("A", "B", "C")
                .delayElements(Duration.ofSeconds(1)) // This dealy applies only for the first three elements
                .concatWith(Flux.error(new RuntimeException("Original Exception!")))
                .concatWith(Flux.just("D"))
                .onErrorResume(error -> {
                    System.out.println("onErrorResume caught: " + error.getMessage());
                    return Flux.just("Fallback1", "Fallback2");
                });
    }
}
