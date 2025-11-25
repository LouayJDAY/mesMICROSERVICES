package com.nadhem.animal.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadhem.animal.dto.APIResponseDto;
import com.nadhem.animal.dto.AnimalDto;
import com.nadhem.animal.dto.GroupeDto;
import com.nadhem.animal.entities.Animal;
import com.nadhem.animal.repos.AnimalRepository;

@Service
public class AnimalServiceImpl implements AnimalService {

    private static final Logger logger = LoggerFactory.getLogger(AnimalServiceImpl.class);

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
        System.out.println("🔍 DEBUG: Calling groupe service for: " + animal.getCodeGroupe());
        
        try {
            // Call groupe service - if it fails, fallback will return "NOT AVAILABLE"
            GroupeDto groupeDto = apiClient.getGroupeByNom(animal.getCodeGroupe());
            System.out.println("✅ DEBUG: groupeDto = " + groupeDto);
            if (groupeDto != null) {
                System.out.println("✅ DEBUG: groupeDto.nomGroupe = " + groupeDto.getNomGroupe());
            }
            logger.info("✅ Groupe service response: {}", groupeDto);
            
            String nomGrp = groupeDto != null ? groupeDto.getNomGroupe() : "NOT AVAILABLE";
            System.out.println("✅ DEBUG: nomGrp = " + nomGrp);
            
            AnimalDto animalDto = new AnimalDto(
                animal.getCodeAnimal(),
                animal.getNomAnimal(),
                animal.getPoidsAnimal(),
                animal.getDateNaissance(),
                animal.getCodeGroupe(),
                nomGrp
            );
            
            APIResponseDto apiResponseDto = new APIResponseDto();
            apiResponseDto.setAnimalDto(animalDto);
            apiResponseDto.setGroupeDto(groupeDto);
            return apiResponseDto;
        } catch (Exception e) {
            System.out.println("❌ DEBUG: Exception: " + e.getMessage());
            logger.error("❌ Error calling groupe service: {}", e.getMessage());
            
            // Fallback: return animal without groupe info
            AnimalDto animalDto = new AnimalDto(
                animal.getCodeAnimal(),
                animal.getNomAnimal(),
                animal.getPoidsAnimal(),
                animal.getDateNaissance(),
                animal.getCodeGroupe(),
                "NOT AVAILABLE"
            );
            
            APIResponseDto apiResponseDto = new APIResponseDto();
            apiResponseDto.setAnimalDto(animalDto);
            apiResponseDto.setGroupeDto(null);
            return apiResponseDto;
        }
    }
}
