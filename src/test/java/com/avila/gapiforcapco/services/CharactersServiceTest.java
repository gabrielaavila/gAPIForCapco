package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CharactersServiceTest {
    private final SwapiConsumer swapiConsumerService = mock(SwapiConsumerService.class);
    private final CharactersService charactersService = new CharactersService(swapiConsumerService);

    @Test
    public void given_CorrectId_When_getCharacterById_Then_ReturnCorrectDTOObject() {
        Person person = mock(Person.class);
        Film film = mock(Film.class);
        List<Film> films = Arrays.asList(film, film);
        String url = "film";
        Integer id = 10;
        List<String> filmsUrls = Arrays.asList(url, url);


        when(swapiConsumerService.getPersonById(id)).thenReturn(person);
        when(person.getFilms()).thenReturn(filmsUrls);
        when(swapiConsumerService.getListOfFilmsByUrl(filmsUrls)).thenReturn(films);
        when(person.getName()).thenReturn("personName");
        when(person.getBirth_year()).thenReturn("someYear");
        when(film.getTitle())
                .thenReturn("firstTitle")
                .thenReturn("secondTitle");

        PersonResponse actualResponse = charactersService.getCharacterById(id);

        assertEquals("personName", actualResponse.getName());
        assertEquals("someYear", actualResponse.getBirth_year());
        Assertions.assertFalse(actualResponse.getFilms().isEmpty());
        assertEquals(2, actualResponse.getFilms().size());
        assertEquals("firstTitle", actualResponse.getFilms().get(0).getTitle());
        assertEquals("secondTitle", actualResponse.getFilms().get(1).getTitle());
    }

    @Test
    public void given_CharactersList_when_getAllCharactersOrdered_Then_returnsFinalDTOListOrderedByNumberOfFilms() {
        Person person1 = mock(Person.class);
        Person person2 = mock(Person.class);
        Person person3 = mock(Person.class);
        List<Person> characters = Arrays.asList(person1, person2, person3);
        String film = "film";
        List<String> films1 = Arrays.asList(film, film);
        List<String> films2 = Arrays.asList(film, film, film);
        List<String> films3 = Collections.singletonList(film);

        when(swapiConsumerService.getAllCharacters()).thenReturn(characters);
        when(person1.getName()).thenReturn("person1");
        when(person1.getFilms()).thenReturn(films1);
        when(person2.getName()).thenReturn("person2");
        when(person2.getFilms()).thenReturn(films2);
        when(person3.getName()).thenReturn("person3");
        when(person3.getFilms()).thenReturn(films3);

        List<PersonWithFilmsQty> result = charactersService.getAllCharactersOrderedByNumberOfFilms();

        assertEquals(3, result.size());
        assertEquals("person3", result.get(0).getName());
        assertEquals(1, result.get(0).getNumberOfFilms());
        assertEquals("person1", result.get(1).getName());
        assertEquals(2, result.get(1).getNumberOfFilms());
        assertEquals("person2", result.get(2).getName());
        assertEquals(3, result.get(2).getNumberOfFilms());
    }

    @Test
    public void given_CharactersListWithSameNumberOfFilms_when_getAllCharactersOrdered_Then_returnsFinalDTOListAlphabeticalOrdered() {
        Person person1 = mock(Person.class);
        Person person2 = mock(Person.class);
        Person person3 = mock(Person.class);
        List<Person> characters = Arrays.asList(person1, person2, person3);
        String film = "film";
        List<String> films = Arrays.asList(film, film);

        when(swapiConsumerService.getAllCharacters()).thenReturn(characters);
        when(person1.getName()).thenReturn("Alfred");
        when(person1.getFilms()).thenReturn(films);
        when(person2.getName()).thenReturn("Richard");
        when(person2.getFilms()).thenReturn(films);
        when(person3.getName()).thenReturn("BECK");
        when(person3.getFilms()).thenReturn(films);

        List<PersonWithFilmsQty> result = charactersService.getAllCharactersOrderedByNumberOfFilms();

        assertEquals(3, result.size());
        assertEquals("Alfred", result.get(0).getName());
        assertEquals(2, result.get(0).getNumberOfFilms());
        assertEquals("BECK", result.get(1).getName());
        assertEquals(2, result.get(1).getNumberOfFilms());
        assertEquals("Richard", result.get(2).getName());
        assertEquals(2, result.get(2).getNumberOfFilms());
    }

    @Test
    public void given_PeopleList_when_getAverageMassOfHumans_Then_getAvMassAndPersonAndMassPeople() {
        Person person = mock(Person.class);
        Specie specie = mock(Specie.class);
        String specieName = "specie";
        List<String> peopleUrl = Arrays.asList("url1", "url2", "url3");
        List<Person> people = Arrays.asList(person, person, person);

        when(swapiConsumerService.getSpecieByName(specieName)).thenReturn(specie);
        when(specie.getPeopleUrl()).thenReturn(peopleUrl);
        when(swapiConsumerService.getPeopleByUrl(peopleUrl)).thenReturn(people);
        when(person.getMass())
                .thenReturn("10")
                .thenReturn("20")
                .thenReturn("30")
                .thenReturn("10")
                .thenReturn("20")
                .thenReturn("30");

        when(person.getName())
                .thenReturn("person1")
                .thenReturn("person2")
                .thenReturn("person3");

        HumansPeopleAndAvMass result = charactersService.getAverageMassOfHumansCharacters(specieName);

        assertEquals("20.0", result.getAverageMass());
        assertEquals(3, result.getPeople().size());
        assertEquals("person1", result.getPeople().get(0).getName());
        assertEquals("person2", result.getPeople().get(1).getName());
        assertEquals("person3", result.getPeople().get(2).getName());

    }

    @Test
    public void given_PeopleListWithUnknownPeopleMass_when_getAverageMassOfHumans_Then_peopleWithUnknownMassDisconsideredForAverageMass() {
        Person person1 = mock(Person.class);
        Person person2 = mock(Person.class);
        Person person3 = mock(Person.class);
        Specie specie = mock(Specie.class);
        String specieName = "specie";
        List<String> peopleUrl = Arrays.asList("url1", "url2", "url3");
        List<Person> people = Arrays.asList(person1, person2, person3);

        when(swapiConsumerService.getSpecieByName(specieName)).thenReturn(specie);
        when(specie.getPeopleUrl()).thenReturn(peopleUrl);
        when(swapiConsumerService.getPeopleByUrl(peopleUrl)).thenReturn(people);
        when(person1.getName()).thenReturn("person1");
        when(person1.getMass()).thenReturn("10");
        when(person2.getName()).thenReturn("person2");
        when(person2.getMass()).thenReturn("20");
        when(person3.getName()).thenReturn("person3");
        when(person3.getMass()).thenReturn("unknown");


        HumansPeopleAndAvMass result = charactersService.getAverageMassOfHumansCharacters(specieName);

        assertEquals("15.0", result.getAverageMass());
        assertEquals(3, result.getPeople().size());
        assertEquals("person1", result.getPeople().get(0).getName());
        assertEquals("person2", result.getPeople().get(1).getName());
        assertEquals("person3", result.getPeople().get(2).getName());

    }


}