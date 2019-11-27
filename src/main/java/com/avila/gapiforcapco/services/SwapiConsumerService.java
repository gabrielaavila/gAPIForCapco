package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.Film;
import com.avila.gapiforcapco.dtos.Person;
import com.avila.gapiforcapco.dtos.Specie;

import java.util.List;

public interface SwapiConsumerService {

    List<Person> getAllCharacters();

    Person getPersonById(Integer id);

    List<Film> getListOfFilmsByUrl(List<String> filmUrls);

    Specie getSpecieByName(String name);

    List<Person> getPeopleByUrl(List<String> peopleUrls);


}
