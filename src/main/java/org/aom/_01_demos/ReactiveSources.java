package org.aom._01_demos;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * This class is a source of reactive streams used in the exercises.
 * DO NOT MODIFY THIS CODE
 *
 * @author koushikkothagal
 */
public class ReactiveSources {

    public static Flux<String> stringNumbersFlux() {
        return Flux.just("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
                .delayElements(Duration.ofSeconds(1));
    }

    public static Flux<Integer> intNumbersFlux() {
        return Flux
                .range(1, 10)
                .delayElements(Duration.ofSeconds(1));
    }

    public static Flux<Integer> intNumbersFluxWithException() {
        return Flux
                .range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .map(e -> {
                    if (e == 5) throw new RuntimeException("An error happened in the flux");
                    return e;
                });
    }

    public static Mono<Integer> intNumberMono() {
        return Mono.just(42)
                .delayElement(Duration.ofSeconds(1));
    }

    public static Flux<User> userFlux() {
        return Flux.just(
                new User(1, "Lionel", "Messi"),
                new User(2, "Cristiano", "Ronaldo"),
                new User(2, "Diego", "Maradona"),
                new User(4, "Zinedine", "Zidane"),
                new User(5, "Jürgen", "Klinsmann"),
                new User(6, "Gareth", "Bale")
        ).delayElements(Duration.ofSeconds(1));
    }

    public static Mono<User> userMono() {
        return Mono.just(
                new User(1, "Lionel", "Messi")
        ).delayElement(Duration.ofSeconds(1));

    }

    public static Flux<String> unresponsiveFlux() {
        return Flux.never();
    }

    public static Mono<String> unresponsiveMono() {
        return Mono.never();
    }

    public static Flux<Integer> intNumbersFluxWithRepeat() {
        return Flux
                .just(1, 2, 1, 1, 3, 2, 4, 5, 1)
                .delayElements(Duration.ofSeconds(1));
    }

    public static Flux<String> randomNamesFlux() {
        return Flux.just("John", "Jane", "Jack", "Jill", "James", "Albin", "Anna")
                .delayElements(Duration.ofSeconds(1));
        //.repeat(2); // Repeat the sequence 2 times
    }

    // a method that creates a flux of random names starting with 'A'
    public static Flux<String> randomNamesFluxStartingWithA() {
        return Flux.just("Albin", "Anna", "Aaron", "Ava")
                .delayElements(Duration.ofSeconds(1));
    }

    // a method that creates a flux of random names starting with 'S'
    public static Flux<String> randomNamesFluxStartingWithS() {
        return Flux.just("Sam", "Sally", "Steve", "Susan")
                .delayElements(Duration.ofSeconds(1));
    }

    public static Publisher<String> randomNameMono() {
        return Mono.just("XYZ")
                .delayElement(Duration.ofSeconds(1));
    }

    public static Publisher<? extends Integer> intNumbersFallbackFlux() {
        return Flux.just(-1, -2)
                .delayElements(Duration.ofSeconds(1));
    }
}
