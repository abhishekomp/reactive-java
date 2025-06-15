package org.aom._03_logback_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class LogbackLoggingDemo {
    private static final Logger log = LoggerFactory.getLogger(LogbackLoggingDemo.class);

    public static void main(String[] args) {
        log.info("Starting Project Reactor stream...");

        Flux<String> fruitFlux = Flux.just("Apple", "Banana", "Orange")
            .delayElements(java.time.Duration.ofSeconds(1)) // Simulate delay for each fruit
            .doOnSubscribe(sub -> log.info("Subscribed!"))
            .doOnNext(fruit -> log.info("Processing fruit: {}", fruit))
            .doOnError(error -> log.error("Error occurred: {}", error.getMessage()))
            .doOnComplete(() -> log.info("All fruits processed."))
            .map(fruit -> {
                log.debug("About to process fruit: {}", fruit); // Log at DEBUG level. Refer the logback.xml file for the log level configuration. You must use the fully qualified class name for the logger.
                if ("Banana".equals(fruit)) throw new RuntimeException("Banana error!");
                return fruit.toUpperCase();
            })
            .onErrorResume(error -> {
                log.warn("Recovering from error: {}", error.getMessage());
                return Flux.just("FRUIT ERROR");
            });

        fruitFlux.subscribe(
            item -> log.info("Subscriber received: {}", item),
            err -> log.error("Subscriber got error: {}", err.getMessage()),
            () -> log.info("Subscriber received stream completion signal.")
        );

        // Keep the application running long enough to see the emissions
        log.info("Press a key to end");
        try {
            System.in.read(); // Wait for user input to end the application
        } catch (Exception e) {
            log.error("Error while waiting for input: {}", e.getMessage());
        }
    }
}