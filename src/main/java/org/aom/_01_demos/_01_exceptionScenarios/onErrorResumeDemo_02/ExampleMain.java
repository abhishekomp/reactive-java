package org.aom._01_demos._01_exceptionScenarios.onErrorResumeDemo_02;

public class ExampleMain {
    public static void main(String[] args) {
        OnErrorResumeDemo.createAndHandleFlux()
                .subscribe(
                        item -> System.out.println("[onNext] Received: " + item),
                        error -> System.out.println("[onError] Error: " + error.getMessage()),
                        () -> System.out.println("[onComplete] Done!")
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