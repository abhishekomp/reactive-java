package org.aom._01_demos._01_exceptionScenarios.onErrorResumeDemo_01;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySubscriber implements Subscriber<String> {
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
        System.out.println("[Subscriber] Subscribed");
        s.request(Long.MAX_VALUE); // Request everything eagerly
    }

    @Override
    public void onNext(String item) {
        System.out.println("[onNext] Received: " + item);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("[onError] Error: " + t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("[onComplete] Done!");
    }
}