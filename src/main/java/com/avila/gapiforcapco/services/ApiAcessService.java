package com.avila.gapiforcapco.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class ApiAcessService implements ApiAccess {

    private final RestTemplate template;
    private final HttpEntity<String> entity;

    public ApiAcessService() {
        this.template = new RestTemplate();
        this.entity = new HttpEntity<>(getHeaders());
    }

    @Override
    public RestTemplate getRestTemplate() {
        return this.template;
    }

    @Override
    public HttpEntity<String> getEntity() {
        return this.entity;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        return headers;
    }
}
