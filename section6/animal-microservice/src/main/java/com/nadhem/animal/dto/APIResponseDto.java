package com.nadhem.animal.dto;

public class APIResponseDto {
    private AnimalDto animalDto;
    private GroupeDto groupeDto;

    public APIResponseDto() {
    }

    public APIResponseDto(AnimalDto animalDto, GroupeDto groupeDto) {
        this.animalDto = animalDto;
        this.groupeDto = groupeDto;
    }

    public AnimalDto getAnimalDto() {
        return animalDto;
    }

    public void setAnimalDto(AnimalDto animalDto) {
        this.animalDto = animalDto;
    }

    public GroupeDto getGroupeDto() {
        return groupeDto;
    }

    public void setGroupeDto(GroupeDto groupeDto) {
        this.groupeDto = groupeDto;
    }
}
