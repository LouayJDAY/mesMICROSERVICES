package com.nadhem.animal.service;

import com.nadhem.animal.dto.GroupeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GROUPE", url = "http://localhost:8081")
public interface APIClient {
    @GetMapping("/api/groupes/{nom}")
    GroupeDto getGroupeByNom(@PathVariable("nom") String nom);
}
