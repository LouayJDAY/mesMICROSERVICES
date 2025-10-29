package com.nadhem.groupe.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadhem.groupe.config.Configuration;
import com.nadhem.groupe.dto.GroupeDto;
import com.nadhem.groupe.service.GroupeService;

@RestController
@RequestMapping("/api/groupes")
public class GroupeController {

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Configuration configuration;

    private final GroupeService groupeService;

    public GroupeController(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @GetMapping("/{nom}")
    public ResponseEntity<GroupeDto> getGroupeByNom(@PathVariable("nom") String nom) {
        GroupeDto dto = groupeService.getGroupeByNom(nom);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> handleDefaultGroupRequest() {
        return new ResponseEntity<>("Default response for /api/groupes", HttpStatus.OK);
    }

    @GetMapping("/version")
    public ResponseEntity<String> version() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @GetMapping("/author")
    public ResponseEntity<String> retrieveAuthorInfo() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(configuration.getName() + " " + configuration.getEmail());
    }
}
