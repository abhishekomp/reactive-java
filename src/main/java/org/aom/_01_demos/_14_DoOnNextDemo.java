package org.aom._01_demos;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class _14_DoOnNextDemo {
    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.range(1, 6) // Emits 1, 2, 3, 4, 5
                .delayElements(Duration.ofSeconds(1))   // Simulate a delay of 1 second between emissions
                .doOnNext(n -> System.out.println("Before filter, received: " + n))
                .filter(n -> n % 2 == 0) // Only even numbers through
                .doOnNext(n -> System.out.println("After filter, kept: " + n));
        // Nothing happens until we subscribe to the flux.
        // The doOnNext operators will log messages before and after the filter operation.
        // This is a demonstration of how doOnNext can be used to observe the flow of data through the pipeline.
        // The first doOnNext will log the number before it is filtered, and the second doOnNext will log the number after it has been filtered.
        // The output will show the numbers being processed, both before and after the filter operation.
        // Note: The doOnNext operators are side-effect operators and do not change the data being emitted by the flux.
        // It is important to remember that they are used for logging or performing side effects without altering the data flow.
        // It is a good practice to use doOnNext for debugging or logging purposes, but they should not be used to modify the data in the flux.
        // It is important to subscribe to the flux to see the output, as the flux is lazy and does not execute until there is a subscriber.
        // The delayElements operator simulates a delay of 1 second between emissions, allowing us to see the output in a staggered manner.

        // Subscribe to the flux to see the output
        numbers.subscribe(n -> System.out.println("Subscriber received: " + n));

        // Keep the application running to see the output
        try {
            Thread.sleep(7000); // Sleep for 7 seconds to allow all emissions to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}