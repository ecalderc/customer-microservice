package com.ejemplos.transformacionFlujos;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

public class Ejemplo01 {
    public static void main(String[] args) {

        Flux.fromArray(new String[]{"Tom", "Melissa", "Steve", "Megan"})
                .map(String::toUpperCase)
                .subscribe(System.out::println);

    }
}