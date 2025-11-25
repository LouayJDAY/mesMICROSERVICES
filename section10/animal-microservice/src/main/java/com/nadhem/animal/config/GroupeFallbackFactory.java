package com.nadhem.animal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.nadhem.animal.dto.GroupeDto;
import com.nadhem.animal.service.APIClient;

/**
 * Fallback Factory for APIClient (Groupe Service)
 * Provides fallback response when circuit breaker opens or service is unavailable
 */
@Component
public class GroupeFallbackFactory implements FallbackFactory<APIClient> {
    private static final Logger logger = LoggerFactory.getLogger(GroupeFallbackFactory.class);

    @Override
    public APIClient create(Throwable cause) {
        logger.error("🔴 CIRCUIT BREAKER FALLBACK ACTIVATED - Exception: {}", cause.getMessage(), cause);
        
        return new APIClient() {
            @Override
            public GroupeDto getGroupeByNom(String nom) {
                logger.warn("⚠️ Using FALLBACK for getGroupeByNom('{}') - Groupe service unavailable", nom);
                
                GroupeDto fallbackDto = new GroupeDto();
                fallbackDto.setCodeGroupe(999);
                fallbackDto.setNomGroupe("NOT AVAILABLE");
                
                logger.info("✅ Fallback returned: {}", fallbackDto);
                return fallbackDto;
            }
        };
    }
}
