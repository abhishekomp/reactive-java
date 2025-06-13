package org.aom._01_demos;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

public class _13_ZipTuple_demo01 {
    public static void main(String[] args) throws IOException {
        demonstrateZipTupleWithTwoFluxes();
        System.out.println("Press a key to end");
        System.in.read();
    }

    private static void demonstrateZipTupleWithTwoFluxes() {
        // First data source: names
        Flux<String> names = Flux.just("Alice", "Bob", "Charlie")
                .delayElements(Duration.ofMillis(1000));

        // Second data source: ages
        Flux<Integer> ages = Flux.just(24, 30, 35)
                .delayElements(Duration.ofMillis(1000));

        // zip the above 2 data sources
        //Flux<String> zippedFlux = Flux.zip(names, ages, (name, age) -> name + " is " + age + " years old");
        //Flux<String> zippedFlux = Flux.zip(names, ages, (name, age) -> String.format("%s is %d years old", name, age));
//        Flux<String> zippedFlux = Flux.zip(names, ages, (name, age) -> String.format("%s is %d years old", name, age))
//                .log();
        Flux<String> zippedFlux = Flux.zip(names, ages)
                .map(tuple -> String.format("%s is %d years old", tuple.getT1(), tuple.getT2()))
                .log();

        // Subscribe to the zipped flux
//        zippedFlux.subscribe(
//                result -> System.out.println("Received: " + result),
//                error -> System.err.println("Error: " + error),
//                () -> System.out.println("Completed")
//        );

        // Subscribe to teh zipped flux with a different approach
        zippedFlux.subscribe(
                result -> System.out.println("Received: " + result));


        // Subscribe to the zipped flux
//        zippedFlux.subscribe(
//                result -> System.out.println("Received: " + result),
//                error -> System.err.println("Error: " + error),
//                () -> System.out.println("Completed")
//        );
    }
}
