package com.example.producer;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private static final String TOPIC = "archived_docs";
    @Autowired
    private KafkaTemplate<String, Enseignant> kafkaTemplate;

    public void sendEnseignant(Enseignant enseignant) {
        Message<Enseignant> message = MessageBuilder
                .withPayload(enseignant)
                .setHeader(KafkaHeaders.TOPIC, TOPIC) // Set the topic in the message header
                .build();
        kafkaTemplate.send(message);
    }
}

