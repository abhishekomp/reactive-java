package org.aom._01_demos;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;
import java.time.Duration;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        // TODO: Write code here
//        ReactiveSources.intNumbersFlux().subscribe(
//                number -> System.out.println("Received: " + number),
//                error -> System.err.println("Error occurred: " + error.getMessage()),
//                () -> System.out.println("Flux completed successfully")
//        );

        // Subscribe to a flux using an implementation of BaseSubscriber
        // TODO: Write code here
        //Using the custom subscriber
        ReactiveSources.intNumbersFlux()
                .delaySubscription(Duration.ofSeconds(2))
                .subscribe(new MySubscriber<>());

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T> {
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscription started");
        request(1); // Request all items
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println("Received: " + value);
        request(1);
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        System.err.println("Error occurred: " + throwable.getMessage());
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("Flux completed successfully");
    }
}

