package org.aom._01_demos._01_exceptionScenarios.onErrorResumeDemo_01;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class OnErrorResumeDemo {
    private static Flux<String> createFluxWithError() {
        return Flux.just("A", "B", "C")
                .delayElements(Duration.ofSeconds(1))
                .concatWith(Flux.error(new RuntimeException("Original Exception!")))
                .concatWith(Flux.just("D").delayElements(Duration.ofSeconds(1)));
    }

    public static Flux<String> getHandledFlux() {
        return createFluxWithError()
                .onErrorResume(error -> {
                    System.out.println("onErrorResume caught: " + error.getMessage());
                    return Flux.just("Fallback1", "Fallback2");
                });
    }
}