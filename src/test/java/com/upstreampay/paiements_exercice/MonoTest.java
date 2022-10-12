package com.upstreampay.paiements_exercice;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoTest {

    @Test
    public void testMono(){
        Mono<?> monoString = Mono.just("testMonoData")
                .then(Mono.error(new RuntimeException("An exception occurred")))
                .log();
        monoString.subscribe(System.out::println, (e)->System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        Flux<String> fluxtest = Flux.just("testdata1","fluxdata", "testing","flux")
                .concatWithValues("upstreampay")
                .concatWith(Flux.error(new RuntimeException("An flux error occurred")))
                .concatWithValues("AWS")
                .log();
        fluxtest.subscribe(System.out::println, (e)->System.out.println(e.getMessage()));
    }
}
