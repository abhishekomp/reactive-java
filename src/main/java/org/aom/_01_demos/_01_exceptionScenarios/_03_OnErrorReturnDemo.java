package org.aom._01_demos._01_exceptionScenarios;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class _03_OnErrorReturnDemo {
    /**
     * Creates a Flux that emits a sequence of strings, encounters an error,
     * and then returns a fallback value using onErrorReturn.
     *
     * @return A Flux that emits strings and handles errors with a fallback value.
     */
    public Flux<String> getStringFlux() {
        Flux<String> flux = Flux.just("A", "B")
                .delayElements(Duration.ofSeconds(1))
                .concatWith(Flux.error(new RuntimeException("Something went wrong!")))
                .concatWith(Flux.just("C"))   // This will never be emitted!
                .onErrorReturn("Fallback Value")
                .log();
        return flux;
    }

    public static void main(String[] args) {
        // Create a Flux that emits some values, then an error, and finally a fallback value
        _03_OnErrorReturnDemo demo = new _03_OnErrorReturnDemo();
        Flux<String> flux = demo.getStringFlux();
        // Subscribe to the Flux and handle the emitted values
        flux.subscribe(
                item -> System.out.println("[onNext] Received: " + item),
                error -> System.out.println("[onError] Error: " + error.getMessage()),
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
}