package org.aom._01_demos;

import java.io.IOException;

public class Exercise4 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        // TODO: Write code here
        ReactiveSources
                .intNumberMono()
                .log()
                .subscribe(
                        element -> System.out.println("Received: " + element),
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("Completed")
                );

        // Get the value from the Mono into an integer variable
        // TODO: Write code here
        Integer integer = ReactiveSources
                .intNumberMono()
                .log()
                .block(); // This will block until the Mono emits a value
        System.out.println("Value from Mono: " + integer);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
