package org.aom._01_demos;

import reactor.core.publisher.Flux;

import java.io.IOException;

public class _12_MergeDemo {
    public static void main(String[] args) throws IOException {
        explore_mergeWith();

        //explore_merge();
        System.out.println("Press a key to end");
        System.in.read();
    }

    private static void explore_mergeWith() {
        // Merge two fluxes using mergeWith
        ReactiveSources.randomNamesFluxStartingWithA()
                .mergeWith(ReactiveSources.randomNamesFluxStartingWithS())
                .log()
                .subscribe(name -> System.out.println("Received name: " + name));
        System.out.println("-----------------------------------");
    }

    private static void explore_merge() {
        // Merge two fluxes using Flux.merge
        Flux
                .merge(ReactiveSources.randomNamesFluxStartingWithA(),
                        ReactiveSources.randomNamesFluxStartingWithS())
                .log()
                .subscribe(name -> System.out.println("Received name: " + name));
        System.out.println("-----------------------------------");
    }
}
