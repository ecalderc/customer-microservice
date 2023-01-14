package com.ejemplos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Ejemplo01 {
    public static void main(String[] args) {

        List<Integer> elementosFromMono = new ArrayList<>();
        List<Integer> elementosFromFlux = new ArrayList<>();

        //Mono
        Mono<Integer> mono = Mono.just(18);

        //Flux
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        // Subscription
        mono.subscribe(elementosFromMono::add);
        flux.subscribe(elementosFromFlux::add);

        System.out.println(elementosFromMono);
        System.out.println(elementosFromFlux);

    }
}