package com.ostapchuk.telegram.tourist.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class TouristClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String findMessageByCityName(final String name) {

        final ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity("http://localhost:8081/api/v1/cities" + "/" + name, String.class);
        } catch (HttpStatusCodeException e) {
            return "Could not find city with the given name: " + name;
        }
        return response.getBody();
    }
}
