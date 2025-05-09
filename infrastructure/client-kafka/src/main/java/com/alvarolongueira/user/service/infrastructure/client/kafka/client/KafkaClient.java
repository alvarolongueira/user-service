package com.alvarolongueira.user.service.infrastructure.client.kafka.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaClient {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicUserCreate;
    private final String topicUserUpdate;
    private final String topicUserDelete;

    public KafkaClient(
            @Value("${kafka.topic.user.create}") String topicUserCreate,
            @Value("${kafka.topic.user.update}") String topicUserUpdate,
            @Value("${kafka.topic.user.delete}") String topicUserDelete,
            KafkaTemplate<String, String> kafkaTemplate) {
        this.topicUserCreate = topicUserCreate;
        this.topicUserUpdate = topicUserUpdate;
        this.topicUserDelete = topicUserDelete;
        this.kafkaTemplate = kafkaTemplate;
    }

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
