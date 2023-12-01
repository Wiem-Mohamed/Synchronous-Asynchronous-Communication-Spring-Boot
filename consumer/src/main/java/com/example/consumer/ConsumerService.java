package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ConsumerService {

    private final RestTemplate restTemplate;

    @Autowired
    public ConsumerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @KafkaListener(topics = "archived_docs", groupId = "archived")
    public void consumeJsonMsg( String enseignant) {
        System.out.println(enseignant);
        String microserviceEndpoint = "http://localhost:8081/api/v1/enseignants/add";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(enseignant, headers);
        String enseignantAppUrl = "http://localhost:8081/api/v1/enseignants/add";

        try {
            // Send the HTTP POST request
            ResponseEntity<String> response = restTemplate.exchange(
                    enseignantAppUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            System.out.println("Response from enseignant app: " + response.getBody());
        } catch (Exception e) {

            System.err.println("Error sending HTTP request: " + e.getMessage());
        }
    }
}
