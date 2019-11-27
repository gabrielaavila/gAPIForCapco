package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.*;
import com.avila.gapiforcapco.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SwapiConsumerServiceImplTest {
    private final ApiAccessService apiAccessService = mock(ApiAccessService.class);
    private final SwapiConsumerServiceImpl swapiConsumerService = new SwapiConsumerServiceImpl(apiAccessService);

    @Test
    public void given_PagedPeopleResults_when_getAllCharacters_then_returnListOfCharacters() {
        Person person = mock(Person.class);
        List<Person> results = Arrays.asList(person, person, person);
        String peoplePath = "somePath";
        ResultsPaged<Person> peoplePg1 = new ResultsPaged<>(6, peoplePath, null, results);
        ResultsPaged<Person> peoplePg2 = new ResultsPaged<>(6, null, "previous", results);
        ResourcesPaths paths = mock(ResourcesPaths.class);

        when(apiAccessService.getPaths()).thenReturn(paths);
        when(paths.getPeople()).thenReturn(peoplePath);
        when(apiAccessService.getPeoplePage(peoplePath))
                .thenReturn(peoplePg1)
                .thenReturn(peoplePg2);

        List<Person> result = swapiConsumerService.getAllCharacters();

        assertEquals(6, result.size());
    }

    @Test
    public void given_NullPageResults_when_getAllCharacters_then_throwResourceNotFoundException(){
        String peoplePath = "path";
        ResourcesPaths paths = mock(ResourcesPaths.class);

        when(apiAccessService.getPaths()).thenReturn(paths);
        when(paths.getPeople()).thenReturn(peoplePath);
        when(apiAccessService.getPeoplePage(peoplePath)).thenReturn(null);

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                swapiConsumerService::getAllCharacters);

        assertTrue(ex.getMessage().equals("People not found."));
    }

    @Test
    public void given_NullId_when_getPersonById_then_throwIllegalArgumentException(){

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> swapiConsumerService.getPersonById(null));

        assertTrue(ex.getMessage().equals("Id parameter must not be null"));
    }

    @Test
    public void given_UrlFilmsList_when_getListOfFilmsByUrl_then_returnListOfFilms(){
        String url = "someUrl";
        List<String> filmsUrl = Arrays.asList(url, url, url);
        Film film1 = mock(Film.class);
        Film film2 = mock(Film.class);
        Film film3 = mock(Film.class);

        when(apiAccessService.getFilmByUrl(url))
                .thenReturn(film1)
                .thenReturn(film2)
                .thenReturn(film3);

        when(film1.getTitle()).thenReturn("title1");
        when(film2.getTitle()).thenReturn("title2");
        when(film3.getTitle()).thenReturn("title3");

        List<Film> result = swapiConsumerService.getListOfFilmsByUrl(filmsUrl);

        assertEquals(3, result.size());
        assertEquals("title1", result.get(0).getTitle());
        assertEquals("title2", result.get(1).getTitle());
        assertEquals("title3", result.get(2).getTitle());
    }

    @Test
    public void given_NullUrlFilmsList_when_getListOfFilmsByUrl_then_throwIllegalArgumentException(){

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> swapiConsumerService.getListOfFilmsByUrl(null));

        assertTrue(ex.getMessage().equals("List of url films must not be null"));
    }

    @Test
    public void given_NullName_when_getSpecieByName_then_throwIllegalArgumentException(){

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> swapiConsumerService.getSpecieByName(null));

        assertTrue(ex.getMessage().equals("Specie name must not be null or empty."));
    }

    @Test
    public void given_EmptyName_when_getSpecieByName_then_throwIllegalArgumentException(){
        String name = "";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> swapiConsumerService.getSpecieByName(name));

        assertTrue(ex.getMessage().equals("Specie name must not be null or empty."));
    }

    @Test
    public void given_NullPagedResults_when_getSpecieByName_then_throwResourceNotFoundException(){
        String name = "name";
        ResultsPaged<Specie> species = new ResultsPaged<>(0, null, null, Collections.emptyList());
        ResourcesPaths paths = mock(ResourcesPaths.class);
        String url = "url/";
        String searchurl = "url?search="+name;

        when(apiAccessService.getPaths()).thenReturn(paths);
        when(paths.getSpecies()).thenReturn(url);
        when(apiAccessService.getSpeciesPaged(searchurl)).thenReturn(species);

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> swapiConsumerService.getSpecieByName(name));

        assertTrue(ex.getMessage().equals("Specie with url "+ searchurl + " not found."));
    }

    @Test
    public void given_PagedSpeciesResults_when_getSpecieByName_then_returnSpecie() {
        Specie specie = mock(Specie.class);
        List<Specie> results = Collections.singletonList(specie);
        String name = "name";
        String url = "url/";
        String searchurl = "url?search="+name;
        ResultsPaged<Specie> speciePg1 = new ResultsPaged<>(1, null, null, results);

        ResourcesPaths paths = mock(ResourcesPaths.class);

        when(apiAccessService.getPaths()).thenReturn(paths);
        when(paths.getSpecies()).thenReturn(url);
        when(apiAccessService.getSpeciesPaged(searchurl))
                .thenReturn(speciePg1);
        when(specie.getPeopleUrl()).thenReturn(Collections.singletonList("someUrl"));

        Specie result = swapiConsumerService.getSpecieByName(name);

        assertEquals(1, result.getPeopleUrl().size());
        assertEquals("someUrl", result.getPeopleUrl().get(0));
    }


    @Test
    public void given_emptyFilmsUrlList_when_getListOfFilmsByUrl_then_returnEmptyListOfFilms() {
        List<Film> result = swapiConsumerService.getListOfFilmsByUrl(Collections.emptyList());

        assertTrue(result.isEmpty());
    }

    @Test
    public void given_UrlPeopleList_when_getPeopleByUrl_then_returnListOfPerson(){
        String url = "someUrl";
        List<String> peopleUrl = Arrays.asList(url, url);
        Person person1 = mock(Person.class);
        Person person2 = mock(Person.class);

        when(apiAccessService.getPersonByUrl(url))
                .thenReturn(person1)
                .thenReturn(person2);

        when(person1.getName()).thenReturn("person1");
        when(person2.getName()).thenReturn("person2");


        List<Person> result = swapiConsumerService.getPeopleByUrl(peopleUrl);

        assertEquals(2, result.size());
        assertEquals("person1", result.get(0).getName());
        assertEquals("person2", result.get(1).getName());
    }

    @Test
    public void given_emptyPeopleUrlList_when_getPeopleByUrl_then_returnEmptyListOfPerson() {
        List<Person> result = swapiConsumerService.getPeopleByUrl(Collections.emptyList());

        assertTrue(result.isEmpty());
    }

}