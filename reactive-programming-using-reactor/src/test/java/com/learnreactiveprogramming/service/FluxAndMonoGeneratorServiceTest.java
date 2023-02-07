package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

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
    void listMono() {
        var listMono = fluxAndMonoGeneratorService.listMono();
        StepVerifier.create(listMono)
                .expectNext(List.of("Arka", "Mosfik", "Zareen", "Farhan"))
                .verifyComplete();
    }

    @Test
    void nameFluxUpper() {
        var nameFluxUpper = fluxAndMonoGeneratorService.nameFluxUpper();
        StepVerifier.create(nameFluxUpper)
                .expectNext("ARKA", "RABBI", "MOSFIK", "TAHIYAT", "ZAREEN")
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

    @Test
    void nameFluxFilter() {
        var nameFluxFilter = fluxAndMonoGeneratorService.nameFluxFilter(4);
        StepVerifier.create(nameFluxFilter)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void nameFluxFlatMap() {
        var nameFluxFlatMap = fluxAndMonoGeneratorService.nameFluxFlatMap(4);
        StepVerifier.create(nameFluxFlatMap)
                .expectNext("R", "a", "b")
                .expectNextCount(21)
                .verifyComplete();
    }

    @Test
    void nameFluxFlatMapAsync() {
        var nameFluxFlatMapAsync = fluxAndMonoGeneratorService.nameFluxFlatMapAsync(4);
        StepVerifier.create(nameFluxFlatMapAsync)
                .expectNextCount(24)
                .verifyComplete();
    }

    @Test
    void nameFluxConcatMap() {
        var nameFluxConcatMap = fluxAndMonoGeneratorService.nameFluxConcatMap(4);
        StepVerifier.create(nameFluxConcatMap)
                .expectNext("R", "a", "b", "b", "i")
                .expectNextCount(19)
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMap() {
        int stringLength = 3;
        var monoValue = fluxAndMonoGeneratorService.nameMonoFlatMap(stringLength);

        StepVerifier.create(monoValue)
                .expectNext(List.of("A", "R", "K", "A", "B", "H", "U", "I", "Y", "A", "N"))
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMapMany() {
        int stringLength = 3;
        var monoValue = fluxAndMonoGeneratorService.nameMonoFlatMapMany(stringLength);

        StepVerifier.create(monoValue)
                .expectNext("A", "R", "K", "A")
                .verifyComplete();
    }

    @Test
    void nameFluxTransform() {
        int stringLength = 4;
        var nameFluxTransform = fluxAndMonoGeneratorService.nameFluxTransform(stringLength);
        StepVerifier.create(nameFluxTransform)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void nameFluxTransformDefaultEmpty() {
        int stringLength = 8;
        var nameFluxTransform = fluxAndMonoGeneratorService.nameFluxTransformDefaultEmpty(stringLength);
        StepVerifier.create(nameFluxTransform)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void nameFluxTransformSwitchIfEmpty() {
        int stringLength = 8;
        var nameFluxTransform = fluxAndMonoGeneratorService.nameFluxTransformSwitchIfEmpty(stringLength);
        StepVerifier.create(nameFluxTransform)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void exploreFluxConcat() {
        var concatFlux = fluxAndMonoGeneratorService.exploreFluxConcat();
        StepVerifier.create(concatFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void exploreConcatWithMono() {
        var concatWithFlux = fluxAndMonoGeneratorService.exploreConcatWithMono();

        StepVerifier.create(concatWithFlux)
                .expectNextCount(2)
                .verifyComplete();
    }


    @Test
    void exploreMerge() {
        var mergedValue = fluxAndMonoGeneratorService.exploreMerge();

        StepVerifier.create(mergedValue)
                .expectNext("a","d","b","e","c","f")
                .verifyComplete();
    }

    @Test
    void exploreMergeWithMono() {
        var mergedValue = fluxAndMonoGeneratorService.exploreMergeWithMono();
        StepVerifier.create(mergedValue)
                .expectNext("arka ")
                .expectNext("bhuiyan")
                .verifyComplete();
    }
}