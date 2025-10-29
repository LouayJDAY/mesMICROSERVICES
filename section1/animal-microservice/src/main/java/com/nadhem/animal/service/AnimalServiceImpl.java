package com.nadhem.animal.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nadhem.animal.dto.AnimalDto;
import com.nadhem.animal.entities.Animal;
import com.nadhem.animal.repos.AnimalRepository;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public AnimalDto getAnimalById(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        if (animal.isEmpty()) return null;
        Animal a = animal.get();
        return new AnimalDto(a.getCodeAnimal(), a.getNomAnimal(), a.getPoidsAnimal(), a.getDateNaissance());
    }

    @Override
    public AnimalDto getAnimalByNom(String nom) {
        Animal animal = animalRepository.findByNomAnimal(nom);
        if (animal == null) return null;
        return new AnimalDto(animal.getCodeAnimal(), animal.getNomAnimal(), animal.getPoidsAnimal(), animal.getDateNaissance());
    }
}
