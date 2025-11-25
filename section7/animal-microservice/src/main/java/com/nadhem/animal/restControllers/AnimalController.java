package com.nadhem.animal.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadhem.animal.config.Configuration;
import com.nadhem.animal.dto.APIResponseDto;
import com.nadhem.animal.dto.AnimalDto;
import com.nadhem.animal.dto.AuthorInfoDto;
import com.nadhem.animal.service.AnimalService;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    Configuration configuration;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/author")
    public ResponseEntity<AuthorInfoDto> retrieveAuthorInfo() {
        AuthorInfoDto authorInfo = new AuthorInfoDto(configuration.getName(), configuration.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(authorInfo);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AnimalDto> getAnimalById(@PathVariable("id") Long id) {
        AnimalDto dto = animalService.getAnimalById(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<AnimalDto> getAnimalByNom(@PathVariable("nom") String nom) {
        AnimalDto dto = animalService.getAnimalByNom(nom);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}/with-groupe")
    public ResponseEntity<APIResponseDto> getAnimalByIdWithGroupe(@PathVariable("id") Long id) {
        APIResponseDto dto = animalService.getAnimalByIdWithGroupe(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Convenience mapping so requests to /api/animals/{id} (browser-friendly) return the composite response
    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDto> getAnimalByIdPath(@PathVariable("id") Long id) {
        APIResponseDto dto = animalService.getAnimalByIdWithGroupe(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
