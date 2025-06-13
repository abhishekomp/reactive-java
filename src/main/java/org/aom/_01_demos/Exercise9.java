package org.aom._01_demos;

import java.io.IOException;

public class Exercise9 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Print size of intNumbersFlux after the last item returns
        // TODO: Write code here
        ReactiveSources
                .intNumbersFlux()
                .count()    // This will count the number of items in the Flux and return a Mono<Long>. We can then subscribe to this Mono to get the count.
                .subscribe(
                        count -> System.out.println("Size of intNumbersFlux: " + count),
                        error -> System.err.println("Error occurred: " + error)
                );

        // Collect all items of intNumbersFlux into a single list and print it
        // TODO: Write code here
        ReactiveSources
                .intNumbersFlux()
                .collectList() // This will collect all items into a List and return a Mono<List<Integer>>.
                .subscribe(
                        list -> System.out.println("Collected list: " + list),
                        error -> System.err.println("Error occurred: " + error)
                );

        // Transform to a sequence of sums of adjacent two numbers
        // TODO: Write code here
        ReactiveSources
                .intNumbersFlux()
                .buffer(2) // This will group the items into lists of 2.
                .map(pair -> pair.stream().reduce(0, Integer::sum)) // This will sum each pair of adjacent numbers.
                .subscribe(
                        sum -> System.out.println("Sum of adjacent numbers: " + sum),
                        error -> System.err.println("Error occurred: " + error)
                );

        System.out.println("Press a key to end");
        System.in.read();
    }

}
