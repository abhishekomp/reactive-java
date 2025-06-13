package org.aom._01_demos._01_publisher_subscriber;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ReactiveSources {

    // This class serves as a placeholder for reactive sources.
    // Method that creates a Flux of 5 elements, each delayed by 1 second.
    public static Flux<String> stringFlux() {
        return Flux.just("A", "B", "C", "D", "E")
                .delayElements(Duration.ofSeconds(1));
        // Don't put log() here, put it in the subscriber instead.
    }

    // Method that creates a Flux of 5 integer elements, each delayed by 1 second.
    public static Flux<Integer> integerFlux() {
        return Flux.range(5, 5) // Starts from 5 and emits 5 elements (5, 6, 7, 8, 9)
                .delayElements(Duration.ofSeconds(1));
        // Don't put log() here, put it in the subscriber instead.
    }

    // Method that creates a Mono of a single string element.
    public static Mono<String> stringMono() {
        return Mono.just("Hello, World!")
                .delayElement(Duration.ofSeconds(1));
        // Don't put log() here, put it in the subscriber instead.
    }
}
