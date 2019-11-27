package com.avila.gapiforcapco.controllers;

import com.avila.gapiforcapco.dtos.*;
import com.avila.gapiforcapco.services.CharactersService;
import com.avila.gapiforcapco.services.LogService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("characters")
@Api(value = "Characters Controler")
public class CharactersController {
    private final CharactersService charactersService;
    private final LogService logService;

    public CharactersController(CharactersService charactersService, LogService logService) {
        this.charactersService = charactersService;
        this.logService = logService;
    }

    @ApiOperation(value = "Get all characters from Star Wars movie ordered by number of filmes they performed " +
            "(crescent order) and also alphabetically ordered.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list."),
            @ApiResponse(code = 404, message = "Characters could not be found."),
            @ApiResponse(code = 500, message = "Internal Server Error.")
    })
    @GetMapping("/ordered")
    public ResponseEntity<List<PersonWithFilmsQty>> getAllCharactersOrderedByNumberOfFilms() {

        logService.saveLog("characters/ordered", "getAllCharactersOrderedByNumberOfFilms");
        return new ResponseEntity<>(charactersService.getAllCharactersOrderedByNumberOfFilms(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get some information from a specific character.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved character."),
            @ApiResponse(code = 404, message = "Character could not be found."),
            @ApiResponse(code = 500, message = "Internal Server Error.")
    })
    @GetMapping("/character")
    public ResponseEntity<PersonResponse> getCharacterById(Integer id) {

        logService.saveLog("characters/character","getCharacterById");
        return new ResponseEntity<>(charactersService.getCharacterById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get the average mass of all humans characters of Star Wars movies and the list with each" +
            "human character and its mass. Characters with 'unknown' mass are disconsidered from average calculation.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list."),
            @ApiResponse(code = 404, message = "Any human character was found."),
            @ApiResponse(code = 500, message = "Internal Server Error.")
    })
    @GetMapping(value = "/humans/averagemass")
    public ResponseEntity<HumansPeopleAndAvMass> getAverageMassOfHumansCharacters() {

        logService.saveLog("characters/humans/averagemass", "getAverageMassOfHumansCharacters");
        return new ResponseEntity<>(charactersService.getAverageMassOfHumansCharacters("human"), HttpStatus.OK);
    }
}
