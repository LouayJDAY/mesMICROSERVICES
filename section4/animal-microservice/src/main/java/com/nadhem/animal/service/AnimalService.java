package com.nadhem.animal.service;

import com.nadhem.animal.dto.AnimalDto;
import com.nadhem.animal.dto.APIResponseDto;

public interface AnimalService {
    AnimalDto getAnimalById(Long id);
    AnimalDto getAnimalByNom(String nom);
    APIResponseDto getAnimalByIdWithGroupe(Long id);
}
