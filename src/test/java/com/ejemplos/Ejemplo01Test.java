package com.ejemplos;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ejemplo01Test {

    @Test
    public void testMonoFluxSubscription() {
        List<Integer> elementosFromMono = new ArrayList<>();
        List<Integer> elementosFromFlux = new ArrayList<>();

        //Mono
        Mono<Integer> mono = Mono.just(18);

        //Flux
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        // Subscription
        mono.subscribe(elementosFromMono::add);
        flux.subscribe(elementosFromFlux::add);

        // assertions
        assertEquals(1, elementosFromMono.size());
        assertEquals(18, (int) elementosFromMono.get(0));
        assertEquals(5, elementosFromFlux.size());
        assertEquals(1, (int) elementosFromFlux.get(0));
        assertEquals(2, (int) elementosFromFlux.get(1));
        assertEquals(3, (int) elementosFromFlux.get(2));
        assertEquals(4, (int) elementosFromFlux.get(3));
        assertEquals(5, (int) elementosFromFlux.get(4));
    }
}
