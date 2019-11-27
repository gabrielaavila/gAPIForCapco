package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.*;

public interface ApiAccessService {

    ResultsPaged<Person> getPeoplePage(String pageUrl);

    Film getFilmByUrl(String filmUrl);

    Person getPersonByUrl(String url);

    ResultsPaged<Specie> getSpeciesPaged(String url);

    ResourcesPaths getPaths();
}
