package org.aom._01_demos;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.function.Function;

public class _10_TransformDemo {
    // Create a flux of 5 random names
    public static void main(String[] args) throws IOException {
//        ReactiveSources.randomNamesFlux()
//                .log()
//                .subscribe(name -> System.out.println("Received name: " + name));
//        System.out.println("-----------------------------------");

        // Transform the above flux to a flux that emits only those names that start with 'A' and in uppercase
//        ReactiveSources.randomNamesFlux()
//                .filter(name -> name.startsWith("A"))
//                .map(String::toUpperCase)
//                .log()
//                .subscribe(name -> System.out.println("Transformed name: " + name));
//        System.out.println("-----------------------------------");

        // Transform the above flux to a flux that emits only those names that are 4 characters in length and in uppercase using transform
        Function<Flux<String>, Flux<String>> fluxPublisherFunction = flux -> flux.filter(name -> name.length() == 5)
                .map(String::toUpperCase);

        ReactiveSources.randomNamesFlux()
                .transform(fluxPublisherFunction)
                .log()
                .switchIfEmpty(Flux.just("No transformed names!"))
                .subscribe(name -> System.out.println("Transformed name with transform: " + name));

        System.out.println("-----------------------------------");

        System.out.println("Press a key to end");
        System.in.read();
    }
}
// Note: The ReactiveSources class is assumed to be defined elsewhere in the project, providing the randomNamesFlux() method.
