package com.nadhem.animal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadhem.animal.dto.AnimalDto;
import com.nadhem.animal.dto.APIResponseDto;
import com.nadhem.animal.dto.GroupeDto;
import com.nadhem.animal.entities.Animal;
import com.nadhem.animal.repos.AnimalRepository;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private APIClient apiClient;

    public AnimalServiceImpl(AnimalRepository animalRepository, APIClient apiClient) {
        this.animalRepository = animalRepository;
        this.apiClient = apiClient;
    }

    @Override
    public AnimalDto getAnimalById(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        if (animal.isEmpty()) return null;
        Animal a = animal.get();
        return new AnimalDto(a.getCodeAnimal(), a.getNomAnimal(), a.getPoidsAnimal(), a.getDateNaissance(), a.getCodeGroupe());
    }

    @Override
    public AnimalDto getAnimalByNom(String nom) {
        Animal animal = animalRepository.findByNomAnimal(nom);
        if (animal == null) return null;
        return new AnimalDto(animal.getCodeAnimal(), animal.getNomAnimal(), animal.getPoidsAnimal(), animal.getDateNaissance(), animal.getCodeGroupe());
    }

    @Override
    public APIResponseDto getAnimalByIdWithGroupe(Long id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        if (optionalAnimal.isEmpty()) {
            return null;
        }
        
        Animal animal = optionalAnimal.get();
        GroupeDto groupeDto = null;
        
        // Appel Feign au groupe-microservice si codeGroupe est défini
        if (animal.getCodeGroupe() != null && !animal.getCodeGroupe().isEmpty()) {
            try {
                groupeDto = apiClient.getGroupeByNom(animal.getCodeGroupe());
            } catch (Exception e) {
                // Gestion silencieuse de l'erreur si le groupe n'existe pas
                groupeDto = null;
            }
        }
        
        AnimalDto animalDto = new AnimalDto(
            animal.getCodeAnimal(),
            animal.getNomAnimal(),
            animal.getPoidsAnimal(),
            animal.getDateNaissance(),
            animal.getCodeGroupe(),
            groupeDto != null ? groupeDto.getNomGroupe() : null
        );
        
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setAnimalDto(animalDto);
        apiResponseDto.setGroupeDto(groupeDto);
        return apiResponseDto;
    }
}
