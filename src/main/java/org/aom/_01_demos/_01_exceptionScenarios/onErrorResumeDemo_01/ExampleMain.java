package org.aom._01_demos._01_exceptionScenarios.onErrorResumeDemo_01;

import reactor.core.publisher.Flux;

public class ExampleMain {
    public static void main(String[] args) {
        // Create a Flux that emits some values and then an error
        Flux<String> handledFlux = OnErrorResumeDemo.getHandledFlux();
        // Subscribe to the Flux and handle the emitted values
        handledFlux.subscribe(new MySubscriber());

        // Keep the application running to see the output
        try {
            System.out.println("Press a key to end");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}