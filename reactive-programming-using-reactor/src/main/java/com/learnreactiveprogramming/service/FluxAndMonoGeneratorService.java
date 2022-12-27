package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoGeneratorService {

    public Flux<String> nameFlux() {
        return Flux.fromIterable(List.of("Arka", "Rabbi", "Mosfik", "Tahiyat", "Zareen")).log();
    }

    public Mono<String> nameMono() {
        return Mono.just("Arka Bhuiyan").log();
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

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
        fluxAndMonoGeneratorService.nameFlux().subscribe(name -> {
            System.out.println("Name is : " + name + ".");
        });

        fluxAndMonoGeneratorService.nameMono().subscribe(name -> {
            System.out.println("Mono Name is : " + name + ".");
        });

        fluxAndMonoGeneratorService.nameFluxUpper().subscribe(name -> {
            System.out.println("Name in Upper case is : " + name + ".");
        });
    }
}
