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
    public void testMonoFluxSubscription() {
        List<Integer> elementosFromMono = new ArrayList<>();
        List<Integer> elementosFromFlux = new ArrayList<>();

        //Mono
        //Mono<Integer> mono = Mono.just(18);

        //Flux
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        // Subscription
        StepVerifier.create(flux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .expectComplete()
                .verify();
    }
}
