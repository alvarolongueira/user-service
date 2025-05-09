package com.alvarolongueira.user.service.infrastructure.client.kafka.adapter;

import com.alvarolongueira.user.service.application.ports.output.UserNotifyOutputPort;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.exception.NotifyCreateException;
import com.alvarolongueira.user.service.infrastructure.client.kafka.client.KafkaClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserNotifyOutputPortAdapter implements UserNotifyOutputPort {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private KafkaClient client;

    @Override
    public void notifyCreate(User user) {
        try {
            String json = objectMapper.writeValueAsString(user);
            client.notifyCreate(json);
        } catch (Exception e) {
            throw new NotifyCreateException(user.getId(), e);
        }
    }

    @Override
    public void notifyUpdate(User user) {
        try {
            String json = objectMapper.writeValueAsString(user);
            client.notifyUpdate(json);
        } catch (Exception e) {
            throw new NotifyCreateException(user.getId(), e);
        }
    }

    @Override
    public void notifyDelete(User user) {
        try {
            String json = objectMapper.writeValueAsString(user);
            client.notifyDelete(json);
        } catch (Exception e) {
            throw new NotifyCreateException(user.getId(), e);
        }
    }
}
