package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.request.People;
import com.avila.gapiforcapco.dtos.request.Person;
import com.avila.gapiforcapco.dtos.request.ResourcesPaths;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    public List<Person> getAllCharacters() {
        List<Person> allCharacters = new ArrayList<>();
        String page = paths.getPeople();
        People people;
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

    private ResourcesPaths getAllPaths(){
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();

        ResourcesPaths resource = restTemplate.exchange(MAIN_URL, HttpMethod.GET, entity, ResourcesPaths.class).getBody();

        return resource;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        return headers;
    }

    private People getPeoplePage(String pageUrl){
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(pageUrl, HttpMethod.GET, entity, People.class).getBody();
    }
}
