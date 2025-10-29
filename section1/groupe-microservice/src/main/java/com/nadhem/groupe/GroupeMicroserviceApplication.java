package com.nadhem.groupe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nadhem.groupe.entities.Groupe;
import com.nadhem.groupe.repos.GroupeRepository;

@SpringBootApplication
public class GroupeMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupeMicroserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(GroupeRepository groupeRepository) {
        return args -> {
            groupeRepository.save(new Groupe(null, "Informatique"));
            groupeRepository.save(new Groupe(null, "Marketing"));
        };
    }
}
