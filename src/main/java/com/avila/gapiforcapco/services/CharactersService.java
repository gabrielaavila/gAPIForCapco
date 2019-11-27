package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.HumansPeopleAndAvMass;
import com.avila.gapiforcapco.dtos.PersonResponse;
import com.avila.gapiforcapco.dtos.PersonWithFilmsQty;

import java.util.List;

public interface CharactersService {

    List<PersonWithFilmsQty> getAllCharactersOrderedByNumberOfFilms();

    PersonResponse getCharacterById(Integer id);

    HumansPeopleAndAvMass getAverageMassOfHumansCharacters(String specieName);
}
