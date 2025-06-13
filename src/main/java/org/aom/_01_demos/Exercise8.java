package org.aom._01_demos;

import java.io.IOException;

public class Exercise8 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print an error message when error happens
        // TODO: Write code here
//        ReactiveSources
//                .intNumbersFluxWithException()
//                .doOnError(error -> System.err.println("Error occurred: " + error.getMessage()))
//                .subscribe(
//                        System.out::println,
//                        error -> System.err.println("Subscription failed with error: " + error)
//                );


        // Print values from intNumbersFluxWithException and continue on errors
        // TODO: Write code here
//        ReactiveSources
//                .intNumbersFluxWithException()
//                .onErrorContinue((error, element) -> System.err.println("Error occurred with element " + element + ": " + error.getMessage()))
//                .subscribe(System.out::println);

        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        // TODO: Write code here
        ReactiveSources
                .intNumbersFluxWithException()
                .onErrorResume(error -> {
                    System.err.println("Error occurred: " + error.getMessage());
                    return ReactiveSources.intNumbersFallbackFlux(); // Assuming this is a Flux that emits -1 and -2
                })
                .subscribe(System.out::println);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
