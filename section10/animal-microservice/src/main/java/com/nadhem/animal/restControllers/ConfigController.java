package com.nadhem.animal.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadhem.animal.config.AppConfig;

/**
 * Controller for Configuration Management
 * Exposes endpoints to view application configuration and test REFRESH functionality
 */
@RestController
@RequestMapping("/api/config")
public class ConfigController {
    
    @Autowired
    private AppConfig appConfig;
    
    /**
     * Endpoint: GET /api/config/app-info
     * Returns: Current application configuration (name, version, author)
     * Use case: View current config before and after REFRESH
     */
    @GetMapping("/app-info")
    public ResponseEntity<String> getAppInfo() {
        String info = String.format(
            "App Name: %s | Version: %s | Author: %s",
            appConfig.getAppName(),
            appConfig.getAppVersion(),
            appConfig.getAuthor()
        );
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }
    
    /**
     * Endpoint: GET /api/config/health
     * Returns: Service health status with configuration loaded indicator
     */
    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.ok("✅ Configuration Service is UP and Ready for REFRESH");
    }
}
