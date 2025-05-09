package com.alvarolongueira.user.service.application.usecases;

import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.domain.entity.PageableUser;
import com.alvarolongueira.user.service.domain.entity.UserSearchRequest;
import com.alvarolongueira.user.service.domain.service.FindAllUsersByUseCase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class FindAllUsersByUseCaseImpl implements FindAllUsersByUseCase {

    private UserDataOutputPort dataOutputPort;

    @Override
    public PageableUser process(UserSearchRequest request) throws Exception {
        return dataOutputPort.findPageableUserList(request);
    }
}
