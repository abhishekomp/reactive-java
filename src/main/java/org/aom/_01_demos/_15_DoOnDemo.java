package org.aom._01_demos;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class _15_DoOnDemo {
    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.range(5, 5) // Generates a range of numbers from 5 to 9
                .delayElements(Duration.ofSeconds(1))
                .doOnSubscribe(sub -> System.out.println("Subscribed!")) // Fires when somebody subscribes
                .doOnNext(n -> System.out.println("Before filter, received: " + n)) // Fires for every item
                .filter(n -> n % 2 == 0)
                .doOnNext(n -> System.out.println("After filter, kept: " + n))     // Fires for items passing filter
                .doOnComplete(() -> System.out.println("Stream complete!")); // Fires after last item and completion

        // Nothing happens until we subscribe to the flux.
        // The doOnNext operators will log messages before and after the filter operation.
        // The doOnSubscribe will log a message when the subscription occurs.
        // The doOnComplete will log a message when the stream completes.
        // This is a demonstration of how doOnNext, doOnSubscribe, and doOnComplete can be used to observe the flow of data through the pipeline.
        // The first doOnNext will log the number before it is filtered, and the second doOnNext will log the number after it has been filtered.
        // The output will show the numbers being processed, both before and after the filter operation.
        // Note: The doOnNext operators are side-effect operators and do not change the data being emitted by the flux.
        numbers.subscribe(n -> System.out.println("Subscriber received: " + n));

        // Keep the application running to see the output
        try {
            Thread.sleep(7000); // Sleep for 7 seconds to allow all emissions to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
// Here is the output you can expect when running this code:
/*
Subscribed!
Before filter, received: 1
Before filter, received: 2
After filter, kept: 2
Subscriber received: 2
Before filter, received: 3
Before filter, received: 4
After filter, kept: 4
Subscriber received: 4
Before filter, received: 5
Before filter, received: 6
After filter, kept: 6
Subscriber received: 6
Stream complete!
 */