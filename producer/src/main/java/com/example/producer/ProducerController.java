package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/producer")
public class ProducerController {
    @Autowired
    private  ProducerService producer;

    @GetMapping(value = "/send")
    public void sendCourseToKafka(@RequestBody Enseignant enseignant) {
    this.producer.sendEnseignant(enseignant);

    }
}
