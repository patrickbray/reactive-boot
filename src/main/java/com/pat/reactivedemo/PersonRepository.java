package com.pat.reactivedemo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

interface PersonRepository extends ReactiveMongoRepository<Person, Integer> {

}
