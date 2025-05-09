package com.alvarolongueira.user.service.infrastructure.client.kafka.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaClient {

    @Value("${kafka.topic.user.create}")
    private String topicUserCreate;

    @Value("${kafka.topic.user.update}")
    private String topicUserUpdate;

    @Value("${kafka.topic.user.delete}")
    private String topicUserDelete;

    private KafkaTemplate<String, String> kafkaTemplate;

    public void notifyCreate(String json) {
        kafkaTemplate.send(topicUserCreate, json);
    }

    public void notifyUpdate(String json) {
        kafkaTemplate.send(topicUserUpdate, json);
    }

    public void notifyDelete(String json) {
        kafkaTemplate.send(topicUserDelete, json);
    }
}
