package com.ejemplos;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ejemplo01Test {

    @Test
    public void testFluxInteger() {

        //Flux
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        // Test
        StepVerifier.create(flux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .expectComplete()
                .verify();
    }

    @Test
    public void testFluxString() {

        //Flux
        Flux<String> flux = Flux.just("Jessica", "Jhon", "Tomas", "Melissa", "Steve", "Megan", "Monica", "Henry")
                .filter(nombre -> nombre.length() <= 5)
                .map(String::toUpperCase);

        // Test
        StepVerifier.create(flux)
                .expectNext("JHON")
                .expectNext("TOMAS")
                .expectNextMatches(nombre -> nombre.startsWith("ST"))
                .expectNext("MEGAN")
                .expectNext("HENRY")
                .expectComplete()
                .verify();
    }
}
