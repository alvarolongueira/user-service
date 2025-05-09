package com.alvarolongueira.user.service.utils;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.alvarolongueira.user.service.domain.entity.User;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.hibernate.tool.schema.Action;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class KafkaConsumerUtils {

    private static final String GROUP_ID = "test-group";
    private static final int SLEEP = 1000;
    private static final int TIMEOUT_SECONDS = 10;
    private static final int POLL_INTERVAL = 500;
    private static final MultiValueMap messages = new MultiValueMap();

    public void validateMessageCreate(User user) {
        validateKafkaMessage(Action.CREATE, user);
    }

    public void validateMessageUpdate(User user) {
        validateKafkaMessage(Action.UPDATE, user);
    }

    public void validateMessageDelete(User user) {
        validateKafkaMessage(Action.DELETE, user);
    }

    public void validateIsEmpty() {
        assertTrue(CollectionUtils.isEmpty(messages.values()));
    }

    private void validateKafkaMessage(Action action, User user) {
        try {
            // TODO remove this
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        await().atMost(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .pollInterval(POLL_INTERVAL, TimeUnit.MILLISECONDS)
                .until(() -> validateMap(action, user));
    }

    @SuppressWarnings("unchecked")
    private Boolean validateMap(Action action, User user) {
        Collection<User> users = messages.getCollection(action);
        Optional<User> userFound = findUser(users, user);
        if (userFound.isPresent()) {
            // TODO compare field by field
            users.remove(userFound.get());
            return true;
        }
        return false;
    }

    private Optional<User> findUser(Collection<User> users, User user) {
        if (CollectionUtils.isEmpty(users)) {
            return Optional.empty();
        }
        return users.stream().filter(current -> current.getId().equals(user.getId())).findFirst();
    }

    @KafkaListener(groupId = GROUP_ID, topics = "${kafka.topic.user.create}")
    public void consumeCreate(ConsumerRecord<String, String> message) {
        messages.put(Action.CREATE, deserialize(message.value()));
    }

    @KafkaListener(groupId = GROUP_ID, topics = "${kafka.topic.user.update}")
    public void consumeUpdate(ConsumerRecord<String, String> message) {
        messages.put(Action.UPDATE, deserialize(message.value()));
    }

    @KafkaListener(groupId = GROUP_ID, topics = "${kafka.topic.user.delete}")
    public void consume(ConsumerRecord<String, String> message) {
        messages.put(Action.DELETE, deserialize(message.value()));
    }

    private User deserialize(String json) {
        return JsonUtils.toObject(json, User.class);
    }

    public enum Action {
        CREATE,
        UPDATE,
        DELETE
    }
}
