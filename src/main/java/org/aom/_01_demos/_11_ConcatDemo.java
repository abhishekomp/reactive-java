package org.aom._01_demos;

import reactor.core.publisher.Flux;

public class _11_ConcatDemo {
    // Concat two fluxes using concat and concatWith
    public static void main(String[] args) throws Exception {

        //exploreConcatWithTwoFlux();

        //explore_concatWith_UsingTwoFlux();

        concatFluxWithMono();

        System.out.println("Press a key to end");
        System.in.read();
    }

    private static void explore_concatWith_UsingTwoFlux() {
        //Concat two fluxes using concatWith
        ReactiveSources.randomNamesFluxStartingWithA()
                .concatWith(ReactiveSources.randomNamesFluxStartingWithS())
                .log()
                .subscribe(name -> System.out.println("Received name: " + name));
        System.out.println("-----------------------------------");
    }

    private static void exploreConcatWithTwoFlux() {
        // Concat two fluxes using concat
        Flux.concat(ReactiveSources.randomNamesFluxStartingWithA(),
                        ReactiveSources.randomNamesFluxStartingWithS()
                )
                .log()
                .subscribe(name -> System.out.println("Received name: " + name));
        System.out.println("-----------------------------------");
    }

    // Concat a Flux with a Mono using concatWith
    public static void concatFluxWithMono() throws Exception {
        ReactiveSources.randomNamesFluxStartingWithA()
                .concatWith(ReactiveSources.randomNameMono())
                .log()
                .subscribe(name -> System.out.println("Received name: " + name));
        System.out.println("-----------------------------------");

        System.out.println("Press a key to end");
        System.in.read();
    }
}
