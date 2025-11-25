package com.nadhem.animal;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.nadhem.animal.entities.Animal;
import com.nadhem.animal.repos.AnimalRepository;

@SpringBootApplication
@EnableFeignClients
public class AnimalMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalMicroserviceApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    CommandLineRunner commandLineRunner(AnimalRepository animalRepository) {
        return args -> {
            animalRepository.save(new Animal(null, "Lion", 190.5, new Date(), "Félin"));
            animalRepository.save(new Animal(null, "Elephant", 5000.0, new Date(), "Pachyderme"));
            animalRepository.save(new Animal(null, "Girafe", 1200.0, new Date(), "Girafidé"));
        };
    }
}
