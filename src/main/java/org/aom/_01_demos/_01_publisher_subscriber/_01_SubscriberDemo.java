package org.aom._01_demos._01_publisher_subscriber;

import java.io.IOException;

public class _01_SubscriberDemo {
    public static void main(String[] args) throws IOException {

        // This is a demo of a simple subscriber consuming elements from a Flux.
        // ReactiveSources.stringFlux().subscribe(System.out::println);

        // This is a demo of a subscriber with log that consumes elements from a Flux
        //ReactiveSources.stringFlux().log().subscribe(System.out::println);

        // This is a demo of a subscriber with log that consumes elements from a Mono
        ReactiveSources.stringMono().log().subscribe(System.out::println);

        // Demonstrating a simple subscriber that consumes elements from a Flux
/*        ReactiveSources.stringFlux()
                .log()
                .subscribe(
                        element -> System.out.println("Received: " + element),
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("Completed")
                );*/

        // Demonstrating a subscriber that consumes elements from an integer Flux
//        ReactiveSources.integerFlux()
//                .log()
//                .subscribe(
//                        element -> System.out.println("Received integer: " + element),
//                        error -> System.err.println("Error: " + error),
//                        () -> System.out.println("Integer Flux Completed")
//                );

        // Keep the application running to observe the output
//        try {
//            Thread.sleep(6000); // Sleep for 6 seconds to allow time for all elements to be processed
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("Press a key to end");
        System.in.read();
    }
}
