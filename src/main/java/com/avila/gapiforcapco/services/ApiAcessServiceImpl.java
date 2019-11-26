package com.avila.gapiforcapco.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiAcessServiceImpl implements ApiAccessService {

    private final RestTemplate template;
    private final HttpEntity<String> entity;

    public ApiAcessServiceImpl() {
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
