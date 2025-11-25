package com.nadhem.animal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Configuration Bean avec @RefreshScope
 * Permet de mettre à jour les propriétés sans redémarrer le service
 */
@Component
@RefreshScope
public class AppConfig {
    
    @Value("${app.name:Animal Service}")
    private String appName;
    
    @Value("${app.version:1.0.0}")
    private String appVersion;
    
    @Value("${app.author:Nadhem}")
    private String author;
    
    public String getAppName() {
        return appName;
    }
    
    public String getAppVersion() {
        return appVersion;
    }
    
    public String getAuthor() {
        return author;
    }
}
