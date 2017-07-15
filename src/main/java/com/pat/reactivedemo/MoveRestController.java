package com.pat.reactivedemo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MoveRestController {

    private final FluxFlixService fluxFlixService;

    private MoveRestController(final FluxFlixService fluxFlixService) {
        this.fluxFlixService = fluxFlixService;
    }

    @GetMapping("/{id}")
    public Mono<Movie> getMovie(@PathVariable String id) {
        return fluxFlixService.byId(id);
    }

    @GetMapping
    public Flux<Movie> allMovies() {
        return fluxFlixService.all();
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> events(@PathVariable String id) {
        final Mono<Movie> movieMono = fluxFlixService.byId(id);
        final Movie movie = movieMono.block();
        if(movie==null) {
            throw new RuntimeException("Movie with id " + id + " not found");
        }
        return fluxFlixService.streams(movie);
    }
}
