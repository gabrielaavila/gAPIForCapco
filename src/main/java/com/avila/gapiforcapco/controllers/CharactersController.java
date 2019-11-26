package com.avila.gapiforcapco.controllers;

import com.avila.gapiforcapco.dtos.*;
import com.avila.gapiforcapco.services.CharactersService;
import com.avila.gapiforcapco.services.LogService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("characters")
@Api(value = "paths")
public class CharactersController {
    private final CharactersService charactersService;
    private final LogService logService;

    public CharactersController(CharactersService charactersService, LogService logService) {
        this.charactersService = charactersService;
        this.logService = logService;
    }

    @GetMapping("/ordered")
    public List<PersonWithFilmsQty> getAllCharactersOrderedByNumberOfFilms() {
        logService.saveLog("characters/ordered", "getAllCharactersOrderedByNumberOfFilms");
        return charactersService.getAllCharactersOrderedByNumberOfFilms();
    }

    @GetMapping("/character")
    public PersonResponse getCharacterById(Integer id) {
        logService.saveLog("characters/character", "getCharacterById");
        return charactersService.getCharacterById(id);
    }

    @GetMapping(value = "/humans/averagemass")
    public HumansPeopleAndAvMass getAverageMassOfHumansCharacters() {
        logService.saveLog("characters/humans/averagemass", "getAverageMassOfHumansCharacters");
        return charactersService.getAverageMassOfHumansCharacters("human");
    }
}
