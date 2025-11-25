package com.nadhem.groupe.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadhem.groupe.config.Configuration;
import com.nadhem.groupe.dto.AuthorInfoDto;
import com.nadhem.groupe.dto.GroupeDto;
import com.nadhem.groupe.service.GroupeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/groupes")
public class GroupeController {

    private final GroupeService groupeService;

    @Autowired
    Configuration configuration;

    public GroupeController(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @GetMapping("/author")
    public ResponseEntity<AuthorInfoDto> retrieveAuthorInfo() {
        AuthorInfoDto authorInfo = new AuthorInfoDto(configuration.getName(), configuration.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(authorInfo);
    }

    @GetMapping("/{nom}")
    public ResponseEntity<GroupeDto> getGroupeByNom(@PathVariable("nom") String nom) {
        GroupeDto dto = groupeService.getGroupeByNom(nom);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<java.util.List<GroupeDto>> getAllGroupes() {
        return new ResponseEntity<>(groupeService.getAllGroupes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GroupeDto> createGroupe(@Valid @RequestBody GroupeDto groupeDto) {
        GroupeDto saved = groupeService.createGroupe(groupeDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupe(@PathVariable("id") Long id) {
        boolean deleted = groupeService.deleteGroupe(id);
        if (!deleted) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<String> handleDefaultGroupRequest() {
        return new ResponseEntity<>("Default response for /api/groupes", HttpStatus.OK);
    }
}
