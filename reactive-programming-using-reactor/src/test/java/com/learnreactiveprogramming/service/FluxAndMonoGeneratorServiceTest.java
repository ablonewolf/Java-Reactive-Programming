package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void nameFlux() {
        var nameFlux = fluxAndMonoGeneratorService.nameFlux();
        StepVerifier.create(nameFlux)
//                .expectNext("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen")
//                .expectNextCount(5)
                .expectNext("Arka")
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void nameFluxUpper() {
        var nameFluxUpper = fluxAndMonoGeneratorService.nameFluxUpper();
        StepVerifier.create(nameFluxUpper)
                .expectNext("ARKA","RABBI","MOSFIK","TAHIYAT","ZAREEN")
//                .expectNext("ARKA")
//                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void nameFluxImmutability() {
        var nameFlux = fluxAndMonoGeneratorService.nameFluxImmutability();
        StepVerifier.create(nameFlux)
//                .expectNext("ARKA","RABBI","MOSFIK","TAHIYAT","ZAREEN")
                .expectNext("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen")
                .verifyComplete();
    }
}