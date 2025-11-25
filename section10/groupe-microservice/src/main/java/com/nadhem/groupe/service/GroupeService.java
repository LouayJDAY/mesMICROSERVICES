package com.nadhem.groupe.service;

import java.util.List;

import com.nadhem.groupe.dto.GroupeDto;

public interface GroupeService {
    GroupeDto getGroupeByNom(String nomGroupe);

    GroupeDto createGroupe(GroupeDto groupeDto);

    List<GroupeDto> getAllGroupes();

    boolean deleteGroupe(Long id);
}
