package com.nadhem.animal.dto;

public class GroupeDto {
    private Long codeGroupe;
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
