package org.aom._01_demos._02_UsersFluxDemo;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxUserDemo_01 {
    public static void main(String[] args) {
        Flux<User> userFlux = Flux.just(
            new User("Alice", 30, UserType.ADMIN),
            new User("Bob", 25, UserType.CUSTOMER),
            new User("Charlie", 20, UserType.GUEST)
        ).delayElements(Duration.ofSeconds(1));

        // Simple subscription: print each user
        userFlux.subscribe(
            user -> System.out.println("Received: " + user),
            error -> System.out.println("Error: " + error),
            () -> System.out.println("All users emitted!")
        );

        // Keep the application running to see the output
        try {
            System.out.println("Press a key to end");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}