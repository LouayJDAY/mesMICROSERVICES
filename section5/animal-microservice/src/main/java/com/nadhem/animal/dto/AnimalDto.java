package com.nadhem.animal.dto;

import java.util.Date;

public class AnimalDto {
    private Long codeAnimal;
    private String nomAnimal;
    private Double poidsAnimal;
    private Date dateNaissance;
    private String codeGroupe;
    private String nomGroupe;

    public AnimalDto() {
    }

    public AnimalDto(Long codeAnimal, String nomAnimal, Double poidsAnimal, Date dateNaissance) {
        this.codeAnimal = codeAnimal;
        this.nomAnimal = nomAnimal;
        this.poidsAnimal = poidsAnimal;
        this.dateNaissance = dateNaissance;
    }

    public AnimalDto(Long codeAnimal, String nomAnimal, Double poidsAnimal, Date dateNaissance, String codeGroupe) {
        this.codeAnimal = codeAnimal;
        this.nomAnimal = nomAnimal;
        this.poidsAnimal = poidsAnimal;
        this.dateNaissance = dateNaissance;
        this.codeGroupe = codeGroupe;
    }

    public AnimalDto(Long codeAnimal, String nomAnimal, Double poidsAnimal, Date dateNaissance, String codeGroupe, String nomGroupe) {
        this.codeAnimal = codeAnimal;
        this.nomAnimal = nomAnimal;
        this.poidsAnimal = poidsAnimal;
        this.dateNaissance = dateNaissance;
        this.codeGroupe = codeGroupe;
        this.nomGroupe = nomGroupe;
    }

    public Long getCodeAnimal() {
        return codeAnimal;
    }

    public void setCodeAnimal(Long codeAnimal) {
        this.codeAnimal = codeAnimal;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public Double getPoidsAnimal() {
        return poidsAnimal;
    }

    public void setPoidsAnimal(Double poidsAnimal) {
        this.poidsAnimal = poidsAnimal;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCodeGroupe() {
        return codeGroupe;
    }

    public void setCodeGroupe(String codeGroupe) {
        this.codeGroupe = codeGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }
}
