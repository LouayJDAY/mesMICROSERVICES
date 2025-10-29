package com.nadhem.animal.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeAnimal;

    @NotNull
    private String nomAnimal;

    private Double poidsAnimal;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private String codeGroupe;

    public Animal() {
    }

    public Animal(Long codeAnimal, String nomAnimal, Double poidsAnimal, Date dateNaissance) {
        this.codeAnimal = codeAnimal;
        this.nomAnimal = nomAnimal;
        this.poidsAnimal = poidsAnimal;
        this.dateNaissance = dateNaissance;
    }

    public Animal(Long codeAnimal, String nomAnimal, Double poidsAnimal, Date dateNaissance, String codeGroupe) {
        this.codeAnimal = codeAnimal;
        this.nomAnimal = nomAnimal;
        this.poidsAnimal = poidsAnimal;
        this.dateNaissance = dateNaissance;
        this.codeGroupe = codeGroupe;
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
}
