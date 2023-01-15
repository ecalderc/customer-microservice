package com.ejemplos.servicios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@SpringBootTest(classes=ServicioSencillo.class)
class ServicioSencilloTest {

    @Autowired
    private ServicioSencillo servicioSencillo;

    @Test
    void buscarUnoTest() {
        Mono<String> uno = servicioSencillo.buscarUno();
        StepVerifier.create(uno).expectNext("Hola").verifyComplete();
    }

    @Test
    void buscarTodosTest() {
        Flux<String> todos = servicioSencillo.buscarTodos();
        StepVerifier.create(todos).expectNext("Hola").expectNext("Mundo").verifyComplete();
    }

    @Test
    void buscarTodosLentoTest() {
        Flux<String> todos = servicioSencillo.buscarTodosLento();
        StepVerifier.create(todos)
                .expectNext("Hola")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("Mundo")
                .thenAwait(Duration.ofSeconds(1))
                .verifyComplete();
    }

}
