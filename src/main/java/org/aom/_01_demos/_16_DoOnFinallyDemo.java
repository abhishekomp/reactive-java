package org.aom._01_demos;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class _16_DoOnFinallyDemo {
    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.range(2, 6) // Generates a range of integers from 2 to 7
                .delayElements(Duration.ofSeconds(1))
                .doOnSubscribe(sub -> System.out.println("Subscribed!"))
                .doOnNext(n -> System.out.println("Before filter, received: " + n))
                .filter(n -> n % 2 == 0)
                .doOnNext(n -> System.out.println("After filter, kept: " + n))
                .map(n -> {
                    if (n == 4) {
                        throw new RuntimeException("Error processing number: " + n);
                    }
                    return n;
                })
                .doOnComplete(() -> System.out.println("Stream complete!"))
                .doFinally(type -> System.out.println("Stream finished with signal: " + type));

        numbers.subscribe(
                n -> System.out.println("Subscriber received: " + n)
        );

        // Keep the application running to see the output
        try {
            Thread.sleep(10000); // Sleep for 10 seconds to allow all emissions to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}