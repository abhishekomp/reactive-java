package org.aom._01_demos._01_exceptionScenarios;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class _04_OnErrorContinueDemo {
    public static void main(String[] args) {

        _04_OnErrorContinueDemo demo = new _04_OnErrorContinueDemo();
        Flux<String> strings = demo.getFlux();

        strings.subscribe(
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

    public Flux<String> getFlux() {
        Flux<Integer> numbers = Flux.just(1, -1, 2, -2, 3)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(num -> System.out.println("[onNext] Processing number: " + num));


        Flux<String> strings = numbers
                .map(num -> {
                    if (num < 0) throw new IllegalArgumentException("Negative number: " + num);
                    return "Number: " + num;
                }).onErrorContinue((throwable, value) -> {
                    System.out.println("Error on value " + value + ": " + throwable.getMessage());
                });
        return strings;
    }
}
// This code demonstrates how to use onErrorContinue to skip over errors in a Flux stream.
// It processes a sequence of integers, allowing the stream to continue even when encountering negative numbers.
// The onErrorContinue operator catches the error and allows the stream to continue processing subsequent values.
// The output will show the processing of each number, and when a negative number is encountered,
// it will print an error message but continue with the next number in the sequence.
// The output will look like this:
/*
 [onNext] Processing number: 1
 [onNext] Received: Number: 1
 [onNext] Processing number: -1
 Error on value -1: Negative number: -1
 [onNext] Processing number: 2
 [onNext] Received: Number: 2
 [onNext] Processing number: -2
 Error on value -2: Negative number: -2
 [onNext] Processing number: 3
 [onNext] Received: Number: 3
 [onComplete] Done!
*/
// This demonstrates the resilience of reactive streams in handling errors gracefully without terminating the entire stream.
// The application will wait for user input before terminating, allowing you to see the output in the console.
// Note: The onErrorContinue operator is useful when you want to skip over errors and continue processing the stream.
// It is particularly useful in scenarios where you expect some errors but want to process the remaining valid data.
// This code is part of the AOM (Advanced Object Model) project, demonstrating exception handling in reactive programming.
// The use of onErrorContinue allows for a more robust and fault-tolerant design in reactive applications,
// enabling the application to handle errors gracefully without losing the entire stream of data.

// onError will not be called because onErrorContinue is used.
// The Subscriber's onError method is not invoked when using onErrorContinue.
