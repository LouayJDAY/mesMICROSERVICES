package com.nadhem.groupe.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuthorInfoDto {
    private String name;
    private String email;
    private String lastModified;

    public AuthorInfoDto(String name, String email) {
        this.name = name;
        this.email = email;
        this.lastModified = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return name + " | " + lastModified + " | " + email;
    }
}
