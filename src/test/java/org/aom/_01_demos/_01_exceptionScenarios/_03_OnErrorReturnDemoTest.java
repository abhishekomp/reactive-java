package org.aom._01_demos._01_exceptionScenarios;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class _03_OnErrorReturnDemoTest {

    @Test
    void testFluxOnErrorReturn() {
        // Arrange
        _03_OnErrorReturnDemo service = new _03_OnErrorReturnDemo();
        // This test verifies that the Flux emits values and returns a fallback value on error

        // Act
        Flux<String> flux = service.getStringFlux();

        // Assert
        StepVerifier.create(flux)
                .expectNext("A")                     // 1st value
                .expectNext("B")                     // 2nd value
                .expectNext("Fallback Value")        // Fallback on error
                .expectComplete()                    // Flux must complete normally, not with error
                .verify();
    }
}