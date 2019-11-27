package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.*;
import com.avila.gapiforcapco.exceptions.ResourceNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiAcessServiceImpl implements ApiAccessService {

    private final RestTemplate template;
    private final HttpEntity<String> entity;
    private final ResourcesPaths paths;
    private static final String MAIN_URL = "https://swapi.co/api/";

    public ApiAcessServiceImpl() {
        this.template = new RestTemplate();
        this.entity = new HttpEntity<>(getHeaders());
        this.paths = getAllPaths();
    }

    @Override
    public ResultsPaged<Person> getPeoplePage(String pageUrl){
        ParameterizedTypeReference<ResultsPaged<Person>> pagedParameterizedTypeReference =
                new ParameterizedTypeReference<ResultsPaged<Person>>() {};
        ResultsPaged<Person> resultsPaged = getRestTemplate()
                .exchange(pageUrl, HttpMethod.GET, getEntity(), pagedParameterizedTypeReference)
                .getBody();

        if (resultsPaged == null)
            throw new ResourceNotFoundException("People with url" + pageUrl+ " not found.");

        return resultsPaged;
    }

    @Override
    public Film getFilmByUrl(String filmUrl) {
        Film film = getRestTemplate()
                .exchange(filmUrl, HttpMethod.GET, getEntity(), Film.class)
                .getBody();

        if (film == null)
            throw new ResourceNotFoundException("Film with url " + filmUrl + " not found.");

        return film;
    }

    @Override
    public Person getPersonByUrl(String url) {
        Person person = getRestTemplate()
                .exchange(url, HttpMethod.GET, getEntity(), Person.class)
                .getBody();

        if (person == null)
            throw new ResourceNotFoundException("Person with url " + url + " not found.");

        return person;
    }

    @Override
    public ResultsPaged<Specie> getSpeciesPaged(String url) {
        ParameterizedTypeReference<ResultsPaged<Specie>> pagedParameterizedTypeReference =
                new ParameterizedTypeReference<ResultsPaged<Specie>>() {};

        ResultsPaged<Specie> specieResultsPaged =  getRestTemplate()
                .exchange(url, HttpMethod.GET, getEntity(), pagedParameterizedTypeReference)
                .getBody();

        if (specieResultsPaged == null)
            throw new ResourceNotFoundException("Specie with url " + url + " not found.");

        return specieResultsPaged;
    }

    @Override
    public ResourcesPaths getPaths() {
        return paths;
    }

    private ResourcesPaths getAllPaths(){
        return getRestTemplate()
                .exchange(MAIN_URL, HttpMethod.GET, getEntity(), ResourcesPaths.class)
                .getBody();
    }

    private RestTemplate getRestTemplate() {
        return this.template;
    }

    private HttpEntity<String> getEntity() {
        return this.entity;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        return headers;
    }

}
