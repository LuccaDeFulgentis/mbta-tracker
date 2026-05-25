package com.mbta.tracker.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MbtaApiClient {

    private final RestTemplate restTemplate;

    @Value("${mbta.api.key}")
    private String apiKey;

    @Value("${mbta.api.base-url}")
    private String baseUrl;

    public MbtaApiClient() {
        this.restTemplate = new RestTemplate();
    }

    public MbtaApiResponse getPredictionsForStop(String stopId) {
        String url = baseUrl + "/predictions?filter[stop]=" + stopId + "&api_key=" + apiKey;
        return restTemplate.getForObject(url, MbtaApiResponse.class);
    }
}