package org.aom._01_demos._02_UsersFluxDemo;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxUserOperationsDemo_02 {
    public static void main(String[] args) {
        Flux<User> userFlux = Flux.just(
            new User("Alice", 30, UserType.ADMIN),
            new User("Bob", 25, UserType.CUSTOMER),
            new User("Charlie", 20, UserType.GUEST),
            new User("ErrorUser", 40, UserType.ADMIN)
        ).delayElements(Duration.ofSeconds(1));

        userFlux
            // Filter: Only include users who are not GUEST
            .filter(user -> user.getType() != UserType.GUEST)

            // Map: Convert user names to uppercase
            .map(user -> new User(user.getName().toUpperCase(), user.getAge(), user.getType()))

            // Simulate an error if a user's name contains "ERROR"
            .map(user -> {
                if (user.getName().contains("ERROR")) {
                    throw new RuntimeException("Name cannot contain 'ERROR': " + user.getName());
                }
                return user;
            })

            // Side effect: print/log on error (does not consume the error)
            .doOnError(error -> System.out.println("[doOnError] Pipeline error: " + error.getMessage()))

            // Error handling: resume with a fallback user instead of terminating
            .onErrorResume(error -> {
                System.out.println("[onErrorResume] Handling: " + error.getMessage());
                return Flux.just(
                    new User("Fallback", 0, UserType.GUEST)
                );
            })

            // Subscribe and consume the results
            .subscribe(
                user -> System.out.println("[onNext] " + user),
                error -> System.out.println("[onError] " + error),
                () -> System.out.println("[onComplete] All done!")
            );

        // Keep the application running to see the output
        try {
            System.out.println("Press a key to end");
            System.in.read(); // Wait for user input to end the application
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}