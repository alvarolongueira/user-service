package com.alvarolongueira.user.service.kafka;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.alvarolongueira.user.service.domain.entity.User;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@Component
public class KafkaConsumerUtils {

    private static final int TIMEOUT_SECONDS = 10;
    private static final int POLL_INTERVAL = 500;
    ConcurrentMap<Action, CopyOnWriteArrayList<User>> messages = new ConcurrentHashMap<>();

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

    protected void put(Action action, User user) {
        messages.computeIfAbsent(action, k -> new CopyOnWriteArrayList<>()).add(user);
    }

    private void validateKafkaMessage(Action action, User user) {
        try {
            // TODO remove this
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        await().atMost(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .pollInterval(POLL_INTERVAL, TimeUnit.MILLISECONDS)
                .until(() -> validateMap(action, user));

        CopyOnWriteArrayList<User> list = messages.get(action);
        if (CollectionUtils.isEmpty(list)) {
            messages.remove(action);
        }
    }

    @SuppressWarnings("unchecked")
    private Boolean validateMap(Action action, User user) {
        List<User> users = messages.get(action);
        Optional<User> userFound = findUser(users, user);
        if (userFound.isPresent()) {
            // TODO compare field by field
            users.remove(userFound.get());
            return true;
        }
        return false;
    }

    private Optional<User> findUser(List<User> users, User user) {
        if (CollectionUtils.isEmpty(users)) {
            return Optional.empty();
        }
        return users.stream().filter(current -> current.getId().equals(user.getId())).findFirst();
    }

    protected enum Action {
        CREATE,
        UPDATE,
        DELETE
    }
}
