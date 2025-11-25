package com.nadhem.animal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Feign Request Interceptor for debugging
 * Logs all feign requests for troubleshooting
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        logger.debug("📤 Feign Request: {} {}", template.method(), template.url());
    }
}
