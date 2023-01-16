package com.ejemplos.excepciones;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo03 {
    public static void main(String[] args) {

        Flux.just(2, 0, 10, 8, 12, 22, 24)
                .map(element -> {
                    if(element == 0){
                        throw new RuntimeException("Error");
                    }
                    return element;
                }).onErrorContinue((ex, element) -> {
                    System.out.println("Exception: " + ex);
                    System.out.println("Elemento: " + element);
                })
                .log()
                .subscribe();

    }
}