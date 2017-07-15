package com.pat.reactivedemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.apache.commons.text.RandomStringGenerator;

@Service
@AllArgsConstructor
public class FluxFlixService {

    private final MovieRepository movieRepository;

    Flux<MovieEvent> streams(Movie movie) {

        final Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        final Flux<MovieEvent> events = Flux.fromStream(
                Stream.generate(
                        () -> new MovieEvent(movie, new Date(), randomUser())
                ));

        return Flux.zip(interval, events)
                .map(Tuple2::getT2);
    }

    private String randomUser() {
        return new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build().generate(5);
    }

    Flux<Movie> all() {
        return movieRepository.findAll();
    }

    Mono<Movie> byId(String id) {
        return movieRepository.findById(id);
    }
}

