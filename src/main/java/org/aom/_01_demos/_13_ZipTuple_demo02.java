package org.aom._01_demos;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

public class _13_ZipTuple_demo02 {
    public static void main(String[] args) throws IOException {
        demonstrateZipTupleWithThreeFluxes();
        System.out.println("Press a key to end");
        System.in.read();
    }

    private static void demonstrateZipTupleWithThreeFluxes() {
        // First data source: names
        Flux<String> names = Flux.just("Alice", "Bob", "Charlie")
                .delayElements(Duration.ofMillis(1000));

        // Second data source: ages
        Flux<Integer> ages = Flux.just(24, 30, 35)
                .delayElements(Duration.ofMillis(1000));

        // Third data source: cities
        Flux<String> cities = Flux.just("New York", "Los Angeles", "Chicago")
                .delayElements(Duration.ofMillis(1000));

        // zip the above 3 data sources
        Flux<String> zippedFlux = Flux.zip(names, ages, cities)
                .map(tuple -> String.format("%s is %d years old and lives in %s", tuple.getT1(), tuple.getT2(), tuple.getT3()))
                .log();

        // Subscribe to the zipped flux
        zippedFlux.subscribe(
                result -> System.out.println("Received: " + result),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );

        // Subscribe to the zipped flux with a different approach
//        zippedFlux.subscribe(
//                result -> System.out.println("Received: " + result));

    }
}
