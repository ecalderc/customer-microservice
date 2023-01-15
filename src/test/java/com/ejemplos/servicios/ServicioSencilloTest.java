package com.ejemplos.servicios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class ServicioSencilloTest {

    @Autowired
    ServicioSencillo servicioSencillo;

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

}
