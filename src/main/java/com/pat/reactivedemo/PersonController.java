package com.pat.reactivedemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class PersonController {

    private final PersonRepository repository;

    private PersonController(final PersonRepository personRepository) {
        this.repository = personRepository;
    }

    @GetMapping("/person")
    public Flux<Person> list() {
        return this.repository.findAll();
    }
}
