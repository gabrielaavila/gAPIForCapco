package com.avila.gapiforcapco.controllers;

import com.avila.gapiforcapco.dtos.request.Person;
import com.avila.gapiforcapco.dtos.request.ResourcesPaths;
import com.avila.gapiforcapco.services.SwapiConsumer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "paths")
public class ResourcesController {
    private final SwapiConsumer swapiConsumerservice;

    @Autowired
    public ResourcesController(SwapiConsumer swapiConsumerservice) {
        this.swapiConsumerservice = swapiConsumerservice;
    }

    @GetMapping(value = "/paths")
    public ResourcesPaths getResourcesPath() {
        return swapiConsumerservice.getPaths();
    }

    @GetMapping(value = "/characters")
    public List<Person> getAllCharacters() {
        return swapiConsumerservice.getAllCharacters();
    }

}