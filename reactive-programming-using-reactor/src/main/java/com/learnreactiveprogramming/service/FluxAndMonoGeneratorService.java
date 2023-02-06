package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
        fluxAndMonoGeneratorService.nameFlux().subscribe(name -> System.out.println("Name is : " + name + "."));

        fluxAndMonoGeneratorService.nameMono().subscribe(name -> System.out.println("Mono Name is : " + name + "."));

        fluxAndMonoGeneratorService.listMono().subscribe(name -> System.out.println("Name in the list: " + name + "." ));

        fluxAndMonoGeneratorService.nameFluxUpper().subscribe(name -> System.out.println("Name in Upper case is : " + name + "."));
    }

    public Flux<String> nameFlux() {
        return Flux.fromIterable(List.of("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen")).log();
    }

    public Mono<String> nameMono() {
        return Mono.just("Arka Bhuiyan").log();
    }

    public Mono<List<String>> listMono() {
        return Mono.just(List.of("Arka","Mosfik","Zareen","Farhan")).log();
    }

    public Mono<String> nameMonoFilterMap(int stringLength) {
        return Mono.just("ArkaBhuiyan")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength);
    }

    public Mono<List<String>> nameMonoFlatMap(int stringLength) {
        return Mono.just("ArkaBhuiyan")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringMono)
                .log();
    }

    public Flux<String> nameMonoFlatMapMany(int stringLength) {
        return Mono.just("Arka")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMapMany(this::splitString)
                .log();
    }

    private Mono<List<String>> splitStringMono(String s) {
        var charArray = s.split("");
        return Mono.just(List.of(charArray));
    }

    public Flux<String> nameFluxUpper() {
        return Flux.fromIterable(List.of("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> nameFluxImmutability() {
        var nameFlux = Flux.fromIterable(List.of("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen"));
        nameFlux.map(String::toUpperCase);
        return nameFlux;
    }

    //    Filter the strings whose length is greater than the given string length
    public Flux<String> nameFluxFilter(int stringLength) {
        return Flux.fromIterable(List.of("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen"))
                .filter(string -> string.length() > stringLength)
                .log();
    }

    public Flux<String> nameFluxFlatMap(int stringLength) {
        return Flux.fromIterable(List.of("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen"))
                .filter(string -> string.length() > stringLength)
                .flatMap(this::splitString)
                .log();
    }

    public Flux<String> nameFluxFlatMapAsync(int stringLength) {
        return Flux.fromIterable(List.of("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen"))
                .filter(string -> string.length() > stringLength)
                .flatMap(this::splitStringWithDelay)
                .log();
    }

    public Flux<String> nameFluxTransform(int stringLength) {
        Function<Flux<String>, Flux<String>> filterMap = name -> name.map(String::toUpperCase).filter(s -> s.length() > stringLength);
        return Flux.fromIterable(List.of("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen"))
                .transform(filterMap)
                .log();
    }

    public Flux<String> nameFluxTransformDefaultEmpty(int stringLength) {
        Function<Flux<String>, Flux<String>> filterMap = name -> name.map(String::toUpperCase).filter(s -> s.length() > stringLength);
        return Flux.fromIterable(List.of("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen"))
                .transform(filterMap)
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> nameFluxTransformSwitchIfEmpty(int stringLength) {
        Function<Flux<String>, Flux<String>> filterMap = name -> name.map(String::toUpperCase).filter(s -> s.length() > stringLength);

        var defaultFlux = Flux.just("default");
        return Flux.fromIterable(List.of("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen"))
                .transform(filterMap)
                .switchIfEmpty(defaultFlux)
                .log();
    }

    public Flux<String> exploreFluxConcat() {
        var firstName = Flux.just("Arka");
        var lastName = Flux.just("Bhuiyan");
        return Flux.concat(firstName,lastName).log();
    }

    public Flux<String> exploreConcatWithMono() {
        var firstName = Mono.just("Arka");
        var lastName = Mono.just("Bhuiyan");
        return firstName.concatWith(lastName).log();
    }

    public Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public Flux<String> splitStringWithDelay(String name) {
        var charArray = name.split("");
        var delay = new Random().nextInt(2000);
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(delay));
    }

    public Flux<String> nameFluxConcatMap(int stringLength) {
        return Flux.fromIterable(List.of("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen"))
                .filter(string -> string.length() > stringLength)
                .concatMap(this::splitStringWithDelay)
                .log();
    }
}
