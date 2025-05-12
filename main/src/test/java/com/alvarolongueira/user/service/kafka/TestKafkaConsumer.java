package com.alvarolongueira.user.service.kafka;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.utils.JsonUtils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestKafkaConsumer {

    private static final String GROUP_ID = "test-group";
    @Autowired private KafkaConsumerUtils kafkaConsumerUtils;

    @KafkaListener(groupId = GROUP_ID, topics = "${kafka.topic.user.create}")
    public void consumeCreate(ConsumerRecord<String, String> message) {
        kafkaConsumerUtils.put(KafkaConsumerUtils.Action.CREATE, deserialize(message.value()));
    }

    @KafkaListener(groupId = GROUP_ID, topics = "${kafka.topic.user.update}")
    public void consumeUpdate(ConsumerRecord<String, String> message) {
        kafkaConsumerUtils.put(KafkaConsumerUtils.Action.UPDATE, deserialize(message.value()));
    }

    @KafkaListener(groupId = GROUP_ID, topics = "${kafka.topic.user.delete}")
    public void consume(ConsumerRecord<String, String> message) {
        kafkaConsumerUtils.put(KafkaConsumerUtils.Action.DELETE, deserialize(message.value()));
    }

    private User deserialize(String json) {
        return JsonUtils.toObject(json, User.class);
    }
}
