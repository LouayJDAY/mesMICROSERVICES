package com.nadhem.groupe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GroupeDto {
    private Long codeGroupe;

    @NotBlank(message = "Le nom du groupe ne doit pas être vide")
    @Size(min = 3, max = 50, message = "Le nom du groupe doit contenir entre 3 et 50 caractères")
    private String nomGroupe;

    public GroupeDto() {
    }

    public GroupeDto(Long codeGroupe, String nomGroupe) {
        this.codeGroupe = codeGroupe;
        this.nomGroupe = nomGroupe;
    }

    public Long getCodeGroupe() {
        return codeGroupe;
    }

    public void setCodeGroupe(Long codeGroupe) {
        this.codeGroupe = codeGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }
}
