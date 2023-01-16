package com.ejemplos.transformacionFlujos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo06 {
    public static void main(String[] args) {

        Flux<String> flux1 = Flux.just("A", "B", "C");
        Flux<String> flux2 = Flux.just("D", "E", "F");

        Flux.zip(flux1, flux2, (first, second) -> first + second).subscribe(System.out::println);

    }

}