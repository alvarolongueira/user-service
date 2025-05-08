package com.alvarolongueira.user.service.domain.service;

public interface UseCase<R, S> {

    S process(R request) throws Exception;
}
