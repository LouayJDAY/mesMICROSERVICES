package com.nadhem.animal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nadhem.animal.dto.GroupeDto;

@Component
public class GroupeFallback implements APIClient {
    private static final Logger logger = LoggerFactory.getLogger(GroupeFallback.class);

    @Override
    public GroupeDto getGroupeByNom(String nom) {
        logger.info("🚨 FALLBACK CALLED for groupe: {}", nom);
        // Si on arrive ici, c'est que le circuit breaker a activé le fallback
        GroupeDto fallbackDto = new GroupeDto();
        fallbackDto.setCodeGroupe(999);
        fallbackDto.setNomGroupe("FALLBACK_CALLED");
        return fallbackDto;
    }
}