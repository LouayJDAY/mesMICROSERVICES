package com.nadhem.groupe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Configuration for Author Information
 * Loads properties from application.yml with prefix "author"
 * 
 * Example:
 * author:
 *   name: "Nadhem"
 *   email: "nadhemb@yahoo.com"
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "author")
public class Configuration {
    
    private String name;
    private String email;
    
    public String getName() {
        return name != null ? name : "Unknown Author";
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email != null ? email : "unknown@email.com";
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Returns formatted author information
     */
    public String getFullInfo() {
        return getName() + " (" + getEmail() + ")";
    }
}
