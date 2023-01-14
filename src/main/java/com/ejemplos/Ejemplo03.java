package com.ejemplos;

import reactor.core.publisher.Mono;

public class Ejemplo03 {
    public static void main(String[] args) {

        Mono<String> mono = Mono.fromSupplier(() -> {
            throw new RuntimeException("Exception");
        });

        mono.subscribe(
                data -> System.out.println(data), //onNext
                err -> System.out.println(err), //onError
                () -> System.out.println("Completado !") //onComplete
        );

    }
}