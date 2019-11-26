package com.avila.gapiforcapco.services;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public interface ApiAccessService {
    RestTemplate getRestTemplate();
    HttpEntity<String> getEntity();

}
