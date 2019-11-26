package com.avila.gapiforcapco.controllers;

import com.avila.gapiforcapco.dtos.*;
import com.avila.gapiforcapco.services.Characters;
import com.avila.gapiforcapco.services.CharactersService;
import com.avila.gapiforcapco.services.SwapiConsumer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("characters")
@Api(value = "paths")
public class CharactersController {
    private final Characters charactersService;

    public CharactersController(Characters charactersService) {
        this.charactersService = charactersService;
    }

    @GetMapping("/ordered")
    public List<PersonWithFilmsQty> getAllCharactersOrderedByNumberOfFilms() {
        return charactersService.getAllCharactersOrderedByNumberOfFilms();
    }

    @GetMapping("/character")
    public PersonResponse getCharacterById(Integer id) {

        return charactersService.getCharacterById(id);
    }

    @GetMapping(value = "/humans/averagemass")
    public HumansPeopleAndAvMass getAverageMassOfHumansCharacters() {
        return charactersService.getAverageMassOfHumansCharacters("human");
    }
}
