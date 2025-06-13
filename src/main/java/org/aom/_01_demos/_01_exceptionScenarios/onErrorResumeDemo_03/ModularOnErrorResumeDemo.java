package org.aom._01_demos._01_exceptionScenarios.onErrorResumeDemo_03;

import reactor.core.publisher.Flux;
import java.time.Duration;

public class ModularOnErrorResumeDemo {

    // Method to create the original Flux with delayed emission and injected error
    public static Flux<String> createDelayedErrorFlux() {
        return Flux.just("A", "B", "C")
                .delayElements(Duration.ofSeconds(1))
                .concatWith(Flux.<String>error(new RuntimeException("Original Exception!"))
                    .delaySubscription(Duration.ofSeconds(1)))
                .concatWith(Flux.just("D").delayElements(Duration.ofSeconds(1)));
    }

    // Method to apply onErrorResume fallback
    public static Flux<String> applyOnErrorResume(Flux<String> sourceFlux) {
        return sourceFlux.onErrorResume(error -> {
            System.out.println("onErrorResume caught: " + error.getMessage());
            return Flux.just("Fallback1", "Fallback2").delayElements(Duration.ofSeconds(1));
        });
    }

    public static void main(String[] args) {
        // Create a Flux with delayed emissions and an error
        Flux<String> fluxWithErrorHandling = applyOnErrorResume(createDelayedErrorFlux());
        // Subscribe to the Flux and handle the emitted values
        fluxWithErrorHandling.subscribe(
            item -> System.out.println("[onNext] Received: " + item),
            error -> System.out.println("[onError] Error: " + error.getMessage()),
            () -> System.out.println("[onComplete] Done!")
        );

        // Keep the application running long enough to see the emissions
        try {
            Thread.sleep(8000); // Adjust in case you want to see all delayed emissions
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// This modular approach allows for better separation of concerns and reusability of the error handling logic.
// The createDelayedErrorFlux method is responsible for creating the Flux with the error,
// while the applyOnErrorResume method handles the error and provides fallback values.
// The main method orchestrates the creation and subscription to the Flux, demonstrating the error handling in action.
// The output will show the sequence of emissions, the error caught by onErrorResume, and the fallback values emitted after the error.
// The delay in the emissions allows you to see the flow of data and the error handling in a more realistic scenario.

// If you run this code, you will see the following output:
// [onNext] Received: A
// [onNext] Received: B
// [onNext] Received: C
// onErrorResume caught: Original Exception!
// [onNext] Received: Fallback1
// [onNext] Received: Fallback2
// [onComplete] Done!
// The output demonstrates the sequence of emissions, the error caught by onErrorResume, and the fallback values emitted after the error.

// Why is this not executed? error -> System.out.println("[onError] Error: " + error.getMessage()),
// because the onErrorResume operator catches the error and replaces it with a new Flux that emits fallback values.
// Therefore, the original error is not propagated to the subscriber's onError method.
// The onErrorResume operator effectively transforms the error into a new stream of fallback values,
// allowing the subscription to continue without terminating with an error.
// This is a key feature of the onErrorResume operator, which allows for graceful error handling and recovery in reactive streams.
// The error does not propagate to the subscriber's onError method because the onErrorResume operator intercepts the error and provides an alternative Flux to continue the stream.
// This allows the stream to continue emitting values even after an error occurs, demonstrating a key aspect of reactive programming: resilience and recovery from errors.
// The onErrorResume operator is designed to handle errors gracefully by providing a fallback mechanism,
// allowing the stream to continue processing without terminating due to an error.

// Detailed explanation of onError() not being called:
/*
Great question!
That line in your `subscribe` call—the error consumer (`onError`)—is **not executed** because of how **`onErrorResume()`** works in Project Reactor.

---

## **Why Isn't the `onError` Callback Called?**

When you use:

```java
fluxWithErrorHandling.subscribe(
    item -> System.out.println("[onNext] Received: " + item),
    error -> System.out.println("[onError] Error: " + error.getMessage()),
    () -> System.out.println("[onComplete] Done!")
);
```

You provide:

- The logic for each next value (`onNext`)
- The error handling logic (`onError`)
- The completion logic (`onComplete`)

---

## **How `onErrorResume()` Changes the Flow**

- **Normally:**
  If the Flux emits an error, the error is passed down and the subscriber’s `onError` handler is called.
- **With `onErrorResume()`:**
  When an error occurs:
  - The `onErrorResume()` operator **intercepts the error**.
  - It then **replaces** the error signal with a new Publisher (your fallback Flux).
  - The new Publisher emits more values to the downstream.
  - **Unless the fallback itself errors, the stream will now complete normally**.

**So:**
- The **error is handled** within the reactive chain, and the error signal **never reaches the subscriber**.
- The stream, from the subscriber’s point of view, completes with a **normal `onComplete` signal** after the fallback values!

---

## **Concrete Flow—Step By Step**

1. `"A"`, `"B"`, `"C"` are emitted → `onNext` called for each.
2. On error:
   - `onErrorResume()` is triggered.
   - error message printed at that point (`onErrorResume caught: ...`).
   - Fallback Flux (`"Fallback1"`, `"Fallback2"`) emits items to the subscriber, `onNext` is called for each.
3. **No error propagates further**.
4. The fallback Flux completes → `onComplete` is called.
5. The `onError` subscriber method is **never** called.

---

## **When Would the `onError` Subscriber Method Execute Then?**

- If **no `onErrorResume()` (or similar) is present**, or
- If the **fallback Flux itself emits an error**,
then the error signal reaches the subscriber and your `onError` consumer runs.

---

## **Summary Table**

| Situation                                           | Subscriber's `onError` Called? |
|-----------------------------------------------------|-------------------------------|
| Error handled by `onErrorResume()`                  | No                            |
| Error not handled (no error operator)               | Yes                           |
| Fallback Publisher (in `onErrorResume()`) errors    | Yes                           |

---

## **Analogy**

- **`onErrorResume()`** is like putting a net in your error pipeline:
  - If you catch the error and keep juggling, the audience (subscriber) never sees a dropped ball (error).
  - Only if you mess up in your recovery and drop another ball (fallback errors), do they notice (the item goes to `onError`).

---

**In summary:**
The line
```java
error -> System.out.println("[onError] Error: " + error.getMessage())
```
**is not executed because `onErrorResume()` absorbs the error, switches to a fallback Flux, and your stream completes normally** after emitting those fallback values.

Let me know if you want a demonstration where both error handlers fire!
 */