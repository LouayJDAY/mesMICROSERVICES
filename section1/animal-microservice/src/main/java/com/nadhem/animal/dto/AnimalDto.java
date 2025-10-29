package com.nadhem.animal.dto;

import java.util.Date;

public class AnimalDto {
    private Long codeAnimal;
    private String nomAnimal;
    private Double poidsAnimal;
    private Date dateNaissance;

    public AnimalDto() {
    }

    public AnimalDto(Long codeAnimal, String nomAnimal, Double poidsAnimal, Date dateNaissance) {
        this.codeAnimal = codeAnimal;
        this.nomAnimal = nomAnimal;
        this.poidsAnimal = poidsAnimal;
        this.dateNaissance = dateNaissance;
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
}
