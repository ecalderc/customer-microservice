package com.ejemplos.transformacionFlujos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo03 {
    public static void main(String[] args) {

        Flux.fromArray(new String[]{"Tom", "Melissa", "Steve", "Megan"})
                .flatMap(Ejemplo03::ponerNombreModificado)
                .subscribe(System.out::println);
    }

    private static Mono<String> ponerNombreModificado(String nombre){
        return Mono.just(nombre.concat(" modificado"));
    }
}