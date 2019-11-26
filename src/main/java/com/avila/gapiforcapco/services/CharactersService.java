package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharactersService implements Characters{

    private final SwapiConsumer swapiConsumerService;

    @Autowired
    public CharactersService(SwapiConsumer swapiConsumerService) {
        this.swapiConsumerService = swapiConsumerService;
    }

    @Override
    public List<PersonWithFilmsQty> getAllCharactersOrderedByNumberOfFilms() {
        List<Person> allCharacters = swapiConsumerService.getAllCharacters();
        List<PersonWithFilmsQty> personWithFilmsQtyList =
                transformListOfPersonIntoListOfPersonWithFilmsQty(allCharacters);

        Collections.sort(personWithFilmsQtyList);

        return personWithFilmsQtyList;
    }

    @Override
    public PersonResponse getCharacterById(Integer id) {
        Person person = swapiConsumerService.getPersonById(id);
        List<Film> films = swapiConsumerService.getListOfFilmsByUrl(person.getFilms());

        return PersonResponse.transformIntoPersonResponse(person, films);
    }

    @Override
    public HumansPeopleAndAvMass getAverageMassOfHumansCharacters(String specieName) {
        Specie humanSpecie = swapiConsumerService.getSpecieByName(specieName);

        List<Person> humanPeople = swapiConsumerService.getPeopleByUrl(humanSpecie.getPeopleUrl());
        Double avMass = getAverageMass(humanPeople);
        List<PersonAndMass> personAndMassList = transformListOfPersonIntoListOfPersonAndMass(humanPeople);

        return new HumansPeopleAndAvMass(personAndMassList, avMass.toString());
    }

    private List<PersonWithFilmsQty> transformListOfPersonIntoListOfPersonWithFilmsQty(List<Person> allCharacters) {
        return allCharacters
                .stream()
                .map(PersonWithFilmsQty::transformIntoPersonWithFilmsQty)
                .collect(Collectors.toList());
    }

    private List<PersonAndMass> transformListOfPersonIntoListOfPersonAndMass(List<Person> people) {
        return people
                .stream()
                .map(PersonAndMass::transformIntoPersonAndMass)
                .collect(Collectors.toList());
    }

    private Double getAverageMass(List<Person> humanPeople) {
        double sumMass = 0;
        int humanPeopleQty = humanPeople.size();

        for (Person person : humanPeople) {
            if (person.getMass().equals("unknown")) {
                humanPeopleQty--;
            } else {
                sumMass += Double.parseDouble(person.getMass());
            }
        }

        return sumMass / humanPeopleQty;
    }
}
