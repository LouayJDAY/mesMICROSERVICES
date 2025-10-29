package com.nadhem.groupe.service;

import org.springframework.stereotype.Service;

import com.nadhem.groupe.dto.GroupeDto;
import com.nadhem.groupe.entities.Groupe;
import com.nadhem.groupe.repos.GroupeRepository;

@Service
public class GroupeServiceImpl implements GroupeService {

    private final GroupeRepository groupeRepository;

    public GroupeServiceImpl(GroupeRepository groupeRepository) {
        this.groupeRepository = groupeRepository;
    }

    @Override
    public GroupeDto getGroupeByNom(String nomGroupe) {
        Groupe groupe = groupeRepository.findByNomGroupe(nomGroupe);
        if (groupe == null) return null;
        return new GroupeDto(groupe.getCodeGroupe(), groupe.getNomGroupe());
    }
}
