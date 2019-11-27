package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.Film;
import com.avila.gapiforcapco.dtos.Person;
import com.avila.gapiforcapco.dtos.ResultsPaged;
import com.avila.gapiforcapco.dtos.Specie;
import com.avila.gapiforcapco.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SwapiConsumerServiceImpl implements SwapiConsumerService {
    private final ApiAccessService apiAccessService;

    @Autowired
    SwapiConsumerServiceImpl(ApiAccessService apiAccessService) {
        this.apiAccessService = apiAccessService;
    }

    @Override
    public List<Person> getAllCharacters() {
        List<Person> allCharacters = new ArrayList<>();
        String peoplePath = apiAccessService.getPaths().getPeople();
        ResultsPaged<Person> people;
        while (true) {
            people = apiAccessService.getPeoplePage(peoplePath);
            if (people == null)
                throw new ResourceNotFoundException("People not found.");

            allCharacters.addAll(people.getResults());

            if (!people.hasNext()) {
                return allCharacters;
            }
            peoplePath = people.getNext();
        }
    }

    @Override
    public Person getPersonById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Id parameter must not be null");

        String url = apiAccessService.getPaths().getPeople() + id;
        return apiAccessService.getPersonByUrl(url);
    }

    @Override
    public List<Film> getListOfFilmsByUrl(List<String> filmUrls) {
        if (filmUrls == null)
            throw new IllegalArgumentException("List of url films must not be null");

        return filmUrls.stream()
                .map(apiAccessService::getFilmByUrl)
                .collect(Collectors.toList());
    }

    @Override
    public Specie getSpecieByName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Specie name must not be null or empty.");

        String speciesPath = apiAccessService.getPaths().getSpecies();
        String url = speciesPath.substring(0, speciesPath.length() - 1) + "?search=" + name;

        ResultsPaged<Specie> resultsPaged = apiAccessService.getSpeciesPaged(url);


        if (resultsPaged.getResults().isEmpty()) {
            throw new ResourceNotFoundException("Specie with url "+ url + " not found.");
        }
        return resultsPaged.getResults().get(0);
    }

    @Override
    public List<Person> getPeopleByUrl(List<String> peopleUrls) {
        if (peopleUrls == null)
            throw new IllegalArgumentException("List of people url must not be null");

        return peopleUrls.stream()
                .map(apiAccessService::getPersonByUrl)
                .collect(Collectors.toList());
    }

}
