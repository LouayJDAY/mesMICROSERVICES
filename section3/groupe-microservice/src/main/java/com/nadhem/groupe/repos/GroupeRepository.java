package com.nadhem.groupe.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadhem.groupe.entities.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {
    Groupe findByNomGroupe(String nomGroupe);
}
