package com.pat.reactivedemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner movieCreator(MovieRepository movieRepository) {

		return args -> {

			final Runnable createTestData = () -> Stream.of("Return of the Jedi",
					"The Fluxinator",
					"Back to the Future")
					.map(name -> new Movie(UUID.randomUUID().toString(), name, randomGenre()))
					.forEach( movie -> movieRepository.save(movie).subscribe(System.out::println));

			movieRepository.deleteAll()
					.subscribe(null,null, createTestData);
		};
	}

	private String randomGenre() {
		final String[] genres = "drama,comedy,romcom,action,thriller".split(",");
		return genres[new Random().nextInt(genres.length)];
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> Flux.fromArray(new String[]{"1", "2", "3", "4"})
            .map(Integer::parseInt)
            .subscribe(System.out::println, null, null);
	}
}
