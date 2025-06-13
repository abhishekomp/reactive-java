package org.aom._01_demos._01_exceptionScenarios;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class _01_ErrorHandlingDemoTest {

    @Test
    void testFluxTerminatesOnErrorAndDoesNotEmitFurtherValues_01() {
        // Arrange
        _01_ErrorHandlingDemo service = new _01_ErrorHandlingDemo();

        // Act
        Flux<String> flux = service.createErrorThenValueFlux();

        // Assert
        StepVerifier.create(flux)
                .expectNext("A")
                .expectNext("B")
                .expectErrorMessage("Boom!") // Expects the exact error message
                .verify();
    }

    @Test
    void testFluxTerminatesOnErrorAndDoesNotEmitFurtherValues_02() {
        // Arrange
        _01_ErrorHandlingDemo service = new _01_ErrorHandlingDemo();

        // Act
        Flux<String> flux = service.createErrorThenValueFlux();

        // Assert
        StepVerifier.create(flux)
                .expectNext("A")
                .expectNext("B")
                .expectError(RuntimeException.class) // Expects a RuntimeException
                .verify();
    }

    @Test
    void testFluxTerminatesOnErrorAndDoesNotEmitFurtherValues_03() {
        // Arrange
        _01_ErrorHandlingDemo service = new _01_ErrorHandlingDemo();

        // Act
        Flux<String> flux = service.createErrorThenValueFlux();

        // Assert
        StepVerifier.create(flux)
                .expectNext("A")
                .expectNext("B")
                .expectError() // Expects any error, not checking the type or message
                .verify();
    }
}