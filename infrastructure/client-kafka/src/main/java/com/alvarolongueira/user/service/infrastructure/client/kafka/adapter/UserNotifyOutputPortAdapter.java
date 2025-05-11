package com.alvarolongueira.user.service.infrastructure.client.kafka.adapter;

import com.alvarolongueira.user.service.application.ports.output.UserNotifyOutputPort;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.exception.NotifyCreateException;
import com.alvarolongueira.user.service.domain.exception.NotifyDeleteException;
import com.alvarolongueira.user.service.domain.exception.NotifyUpdateException;
import com.alvarolongueira.user.service.infrastructure.client.kafka.client.KafkaClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserNotifyOutputPortAdapter implements UserNotifyOutputPort {

    private final ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new JavaTimeModule());
    private KafkaClient client;

    @Override
    public void notifyCreate(User user) {
        try {
            client.notifyCreate(toJson(user));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NotifyCreateException(user.getId(), e);
        }
    }

    @Override
    public void notifyUpdate(User user) {
        try {
            client.notifyUpdate(toJson(user));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NotifyUpdateException(user.getId(), e);
        }
    }

    @Override
    public void notifyDelete(User user) {
        try {
            client.notifyDelete(toJson(user));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NotifyDeleteException(user.getId(), e);
        }
    }

    private String toJson(User user) throws JsonProcessingException {
        return objectMapper.writeValueAsString(user);
    }
}
