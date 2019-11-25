package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SwapiConsumer {
    private static final String MAIN_URL = "https://swapi.co/api/";
    private final ResourcesPaths paths;

    public SwapiConsumer() {
        this.paths = getAllPaths();
    }

    public ResourcesPaths getPaths() {
        return paths;
    }

    public List<PersonWithFilmsQty> getAllCharactersOrderedByNumberOfFilms() {
        List<Person> allCharacters = getAllCharacters();
        List<PersonWithFilmsQty> personWithFilmsQtyList = new ArrayList<>();

        for (Person person : allCharacters) {
            personWithFilmsQtyList.add(PersonWithFilmsQty.transformIntoPersonWithFilmsQty(person));
        }
        Collections.sort(personWithFilmsQtyList);

        return personWithFilmsQtyList;
    }

    private List<Person> getAllCharacters() {
        List<Person> allCharacters = new ArrayList<>();
        String page = paths.getPeople();
        ResultsPaged<Person> people;
        boolean hasNext = true;
        while (hasNext) {
            people = getPeoplePage(page);
            allCharacters.addAll(people.getResults());
            if (!people.hasNext()) {
                return allCharacters;
            }
            page = people.getNext();
        }
        return allCharacters;

    }

    public HumansPeopleAndAvMass getAverageMassOfHumansCharacters() {
        Specie humanSpecie = getSpecieByName("human");

        List<PersonAndMass> humanPeople = getPeopleByUrl(humanSpecie.getPeopleUrl());
        Double avMass = getAverageMass(humanPeople);

        return new HumansPeopleAndAvMass(humanPeople, avMass.toString());
    }

    private Double getAverageMass(List<PersonAndMass> humanPeople) {
        double sumMass = 0;

        for (PersonAndMass person : humanPeople) {
            if (!person.getMass().equals("unknown"))
                sumMass += Double.parseDouble(person.getMass());
        }

        return sumMass / humanPeople.size();
    }

    private List<PersonAndMass> getPeopleByUrl(List<String> peopleUrls) {
        List<PersonAndMass> people = new ArrayList<>();
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        PersonAndMass person;
        for (String url : peopleUrls) {
            person = restTemplate.exchange(url, HttpMethod.GET, entity, PersonAndMass.class).getBody();
            people.add(person);
        }

        return people;
    }

    private Specie getSpecieByName(String name) {
        String url = paths.getSpecies().substring(0, paths.getSpecies().length() - 1) + "?search=" + name;
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<ResultsPaged<Specie>> pagedParameterizedTypeReference =
                new ParameterizedTypeReference<ResultsPaged<Specie>>() {};
        ResultsPaged<Specie> resultsPaged =  restTemplate.exchange(url, HttpMethod.GET, entity, pagedParameterizedTypeReference).getBody();
        if (resultsPaged == null)
            return null; //todo exception handle
        else if (resultsPaged.getResults().isEmpty()) {
            return null; //todo exception handle
        }
        return resultsPaged.getResults().get(0);
    }

    private ResourcesPaths getAllPaths(){
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(MAIN_URL, HttpMethod.GET, entity, ResourcesPaths.class).getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        return headers;
    }

    private ResultsPaged<Person> getPeoplePage(String pageUrl){
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<ResultsPaged<Person>> pagedParameterizedTypeReference =
                new ParameterizedTypeReference<ResultsPaged<Person>>() {};

        return restTemplate.exchange(pageUrl, HttpMethod.GET, entity, pagedParameterizedTypeReference).getBody();
    }

    public PersonResponse getCharacterById(Integer id) {
        Person person = getPersonById(id);
        List<Film> films = new ArrayList<>();
        for (String filmUrl : person.getFilms()) {
             films.add(getFilmByUrl(filmUrl));
        }
        return PersonResponse.transformIntoPersonResponse(person, films);
    }

    private Film getFilmByUrl(String filmUrl) {
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(filmUrl, HttpMethod.GET, entity, Film.class).getBody();
    }

    private Person getPersonById(Integer id) {
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        String url = paths.getPeople() + id;
        return restTemplate.exchange(url, HttpMethod.GET, entity, Person.class).getBody();
    }

}
