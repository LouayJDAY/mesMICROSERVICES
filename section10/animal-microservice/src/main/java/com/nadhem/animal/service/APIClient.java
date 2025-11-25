package com.nadhem.animal.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nadhem.animal.config.GroupeFallbackFactory;
import com.nadhem.animal.dto.GroupeDto;

@FeignClient(name = "GROUPE", fallbackFactory = GroupeFallbackFactory.class)
public interface APIClient {
    @GetMapping("/api/groupes/{nom}")
    GroupeDto getGroupeByNom(@PathVariable("nom") String nom);
}
