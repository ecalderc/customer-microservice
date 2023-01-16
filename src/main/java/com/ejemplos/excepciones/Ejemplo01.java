package com.ejemplos.excepciones;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;

public class Ejemplo01 {
    public static void main(String[] args) {

        Flux.just(2, 7, 10)
                .concatWith(Flux.error(new RuntimeException("Error")))
                .concatWith(Mono.just(12))
                .onErrorReturn(72)
                .log()
                .subscribe();

    }
}