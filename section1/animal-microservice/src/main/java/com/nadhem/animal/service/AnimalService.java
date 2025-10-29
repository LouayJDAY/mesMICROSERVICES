package com.nadhem.animal.service;

import com.nadhem.animal.dto.AnimalDto;

public interface AnimalService {
    AnimalDto getAnimalById(Long id);
    AnimalDto getAnimalByNom(String nom);
}
