package com.alvarolongueira.user.service.infrastructure.rest.api.handler;

import com.alvarolongueira.user.service.domain.service.UseCase;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UseCaseHandler {

    public <S, R> S handle(UseCase<R, S> useCase, R request) throws Exception {
        try {
            return useCase.process(request);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
