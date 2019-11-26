package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SwapiConsumerService implements SwapiConsumer{
    private static final String MAIN_URL = "https://swapi.co/api/";
    private final ResourcesPaths paths;
    private final ApiAccess apiAccessService;
    @Autowired
    SwapiConsumerService(ApiAccess apiAccessService) {
        this.apiAccessService = apiAccessService;
        this.paths = getAllPaths();
    }

    @Override
    public List<Person> getAllCharacters() {
        List<Person> allCharacters = new ArrayList<>();
        String page = paths.getPeople();
        ResultsPaged<Person> people;

        while (true) {
            people = getPeoplePage(page);
            allCharacters.addAll(people.getResults());

            if (!people.hasNext()) {
                return allCharacters;
            }

            page = people.getNext();
        }

    }

    @Override
    public Person getPersonById(Integer id) {
        String url = paths.getPeople() + id;
        return getPersonByUrl(url);
    }

    @Override
    public List<Film> getListOfFilmsByUrl(List<String> filmUrls) {
        return filmUrls.stream()
                .map(this::getFilmByUrl)
                .collect(Collectors.toList());
    }

    @Override
    public Specie getSpecieByName(String name) {
        String url = paths.getSpecies().substring(0, paths.getSpecies().length() - 1) + "?search=" + name;
        ParameterizedTypeReference<ResultsPaged<Specie>> pagedParameterizedTypeReference =
                new ParameterizedTypeReference<ResultsPaged<Specie>>() {};

        ResultsPaged<Specie> resultsPaged = apiAccessService.getRestTemplate()
                .exchange(url, HttpMethod.GET, apiAccessService.getEntity(), pagedParameterizedTypeReference)
                .getBody();

        if (resultsPaged == null)
            return null; //todo exception handle
        else if (resultsPaged.getResults().isEmpty()) {
            return null; //todo exception handle
        }
        return resultsPaged.getResults().get(0);
    }

    @Override
    public List<Person> getPeopleByUrl(List<String> peopleUrls) {
        List<Person> people = new ArrayList<>();
        Person person;

        for (String url : peopleUrls) {
            person = getPersonByUrl(url);
            people.add(person);
        }

        return people;
    }

    private ResourcesPaths getAllPaths(){
        return apiAccessService.getRestTemplate()
                .exchange(MAIN_URL, HttpMethod.GET, apiAccessService.getEntity(), ResourcesPaths.class)
                .getBody();
    }

    private ResultsPaged<Person> getPeoplePage(String pageUrl){

        ParameterizedTypeReference<ResultsPaged<Person>> pagedParameterizedTypeReference =
                new ParameterizedTypeReference<ResultsPaged<Person>>() {};

        return apiAccessService.getRestTemplate()
                .exchange(pageUrl, HttpMethod.GET, apiAccessService.getEntity(), pagedParameterizedTypeReference)
                .getBody();
    }

    private Film getFilmByUrl(String filmUrl) {
        return apiAccessService.getRestTemplate()
                .exchange(filmUrl, HttpMethod.GET, apiAccessService.getEntity(), Film.class)
                .getBody();
    }

    private Person getPersonByUrl(String url) {
        return apiAccessService.getRestTemplate()
                .exchange(url, HttpMethod.GET, apiAccessService.getEntity(), Person.class)
                .getBody();
    }

}
