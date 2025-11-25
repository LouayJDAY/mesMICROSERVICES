package com.nadhem.animal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Custom Feign Error Decoder
 * Converts HTTP errors to exceptions so circuit breaker can catch them
 */
@Component
public class FeignErrorDecoder implements ErrorDecoder {
    private static final Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        logger.error("🚨 Feign Error - Status: {} for method: {}", response.status(), methodKey);
        
        if (response.status() >= 500) {
            logger.error("❌ Server Error detected - Circuit breaker will handle this");
            return new RuntimeException("Groupe service returned HTTP " + response.status() + " - Server Error");
        } else if (response.status() >= 400) {
            logger.warn("⚠️ Client Error detected - HTTP " + response.status());
            return new RuntimeException("Groupe service returned HTTP " + response.status() + " - Client Error");
        }
        
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
