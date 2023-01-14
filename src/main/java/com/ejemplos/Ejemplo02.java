package com.ejemplos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Ejemplo02 {
    public static void main(String[] args) {

        Mono<String> mono = Mono.just("Hola");

        mono.subscribe(
                data -> System.out.println(data), //onNext
                err -> System.out.println(err), //onError
                () -> System.out.println("Completado !") //onComplete
        );

    }
}