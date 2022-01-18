package com.ostapchuk.telegram.tourist.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static com.ostapchuk.telegram.tourist.util.Const.CITIES_ENDPOINT;
import static com.ostapchuk.telegram.tourist.util.Const.MSG_404;
import static com.ostapchuk.telegram.tourist.util.Const.SLASH;

@Component
@RequiredArgsConstructor
public class TouristClient {

    @Value("${application.link}")
    private String applicationLink;

    private final RestTemplate restTemplate;

    public String findMessageByCityName(final String name) {
        final ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(applicationLink + CITIES_ENDPOINT + SLASH + name, String.class);
        } catch (HttpStatusCodeException e) {
            return MSG_404 + name;
        }
        return response.getBody();
    }
}
