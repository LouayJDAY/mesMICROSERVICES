package com.nadhem.animal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupeDto {
    private Integer codeGroupe;
    private String nomGroupe;

    public GroupeDto() {
        // Default values when deserialization fails
        this.nomGroupe = "NOT AVAILABLE";
    }

    public GroupeDto(Integer codeGroupe, String nomGroupe) {
        this.codeGroupe = codeGroupe;
        this.nomGroupe = nomGroupe;
    }

    public Integer getCodeGroupe() {
        return codeGroupe;
    }

    public void setCodeGroupe(Integer codeGroupe) {
        this.codeGroupe = codeGroupe;
    }

    public String getNomGroupe() {
        // Ensure we never return null
        return (nomGroupe == null || nomGroupe.isEmpty()) ? "NOT AVAILABLE" : nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }
}
