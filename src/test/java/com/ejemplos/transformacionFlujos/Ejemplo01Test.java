package com.ejemplos.transformacionFlujos;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Ejemplo01Test {

    @Test
    public void testTransformMap(){
        List<String> listaNombres = Arrays.asList("Google", "Github", "Fb", "StackOverflow");
        Flux<String> nombresFlux = Flux.fromIterable(listaNombres)
                .filter(nombre -> nombre.length() > 5)
                .map(nombre -> nombre.toUpperCase())
                .log();

        StepVerifier.create(nombresFlux)
                .expectNext("GOOGLE", "GITHUB", "STACKOVERFLOW")
                .verifyComplete();
    }

    @Test
    public void testTransformFlapMap(){
        List<String> listaNombres = Arrays.asList("Google", "Github", "Fb", "StackOverflow");
        Flux<String> nombresFlux = Flux.fromIterable(listaNombres)
                .filter(nombre -> nombre.length() > 5)
                .flatMap(nombre -> {
                    return Mono.just(nombre.toUpperCase());
                })
                .log();

        StepVerifier.create(nombresFlux)
                .expectNext("GOOGLE", "GITHUB", "STACKOVERFLOW")
                .verifyComplete();
    }

    @Test
    public void testCombinarMerge(){
        Flux<String> flux1 = Flux.just("Google", "Github", "Fb");
        Flux<String> flux2 = Flux.just("FBI", "Gato", "WhatsApp");

        Flux<String> fluxMerge = Flux.merge(flux1, flux2).log();

        StepVerifier.create(fluxMerge)
                .expectNext("Google", "Github", "Fb", "FBI", "Gato", "WhatsApp")
                .verifyComplete();

    }

    @Test
    public void testCombinarMergeConDemora(){
        Flux<String> flux1 = Flux.just("Google", "Github", "Fb")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("FBI", "Gato", "WhatsApp")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxMerge = Flux.merge(flux1, flux2).log();

        StepVerifier.create(fluxMerge)
                .expectSubscription()
                .expectNextCount(6)
                .verifyComplete();

    }

    @Test
    public void testCombinarConDemoraConcat(){
        Flux<String> flux1 = Flux.just("Google", "Github", "Fb")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("FBI", "Gato", "WhatsApp")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxConcat = Flux.concat(flux1, flux2).log();

        StepVerifier.create(fluxConcat)
                .expectSubscription()
                .expectNext("Google", "Github", "Fb", "FBI", "Gato", "WhatsApp")
                .verifyComplete();

    }

    @Test
    public void testCombinarConDemoraZip(){
        Flux<String> flux1 = Flux.just("Google", "Github", "Fb")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("FBI", "Gato", "WhatsApp")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxZip = Flux.zip(flux1, flux2, (f1, f2) -> {
            return f1.concat(" ").concat(f2);
        }).log();

        StepVerifier.create(fluxZip)
                .expectSubscription()
                .expectNext("Google FBI", "Github Gato", "Fb WhatsApp")
                .verifyComplete();

    }

}