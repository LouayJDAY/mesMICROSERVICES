package com.nadhem.groupe.service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public GroupeDto createGroupe(GroupeDto groupeDto) {
        Groupe g = new Groupe();
        g.setNomGroupe(groupeDto.getNomGroupe());
        Groupe saved = groupeRepository.save(g);
        return new GroupeDto(saved.getCodeGroupe(), saved.getNomGroupe());
    }

    @Override
    public List<GroupeDto> getAllGroupes() {
        return groupeRepository.findAll().stream()
                .map(g -> new GroupeDto(g.getCodeGroupe(), g.getNomGroupe()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteGroupe(Long id) {
        if (!groupeRepository.existsById(id)) return false;
        groupeRepository.deleteById(id);
        return true;
    }
}
