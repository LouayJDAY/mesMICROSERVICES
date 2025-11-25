package com.nadhem.animal.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadhem.animal.entities.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findByNomAnimal(String nomAnimal);
}
