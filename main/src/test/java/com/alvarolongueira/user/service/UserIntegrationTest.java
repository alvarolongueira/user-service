package com.alvarolongueira.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.infrastructure.database.repository.entity.UserEntity;
import com.alvarolongueira.user.service.rest.api.model.CreateUser201Response;
import com.alvarolongueira.user.service.rest.api.model.CreateUserRequest;
import com.alvarolongueira.user.service.rest.api.model.UserModel;
import com.alvarolongueira.user.service.testcontainer.PostgreTestContainer;
import com.alvarolongueira.user.service.utils.DatabaseUtils;
import com.alvarolongueira.user.service.utils.KafkaConsumerUtils;
import com.alvarolongueira.user.service.utils.ModelFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {"listeners=PLAINTEXT://localhost:29092", "port=29092"})
class UserIntegrationTest extends PostgreTestContainer {

    @LocalServerPort private int port;
    @Autowired private TestRestTemplate restTemplate;
    @Autowired private KafkaConsumerUtils kafkaConsumerUtils;
    @Autowired private DatabaseUtils databaseUtils;

    @Test()
    void allApisWorkAsExpected() throws InterruptedException {
        String userId = validatePOST();
        validateGET(userId);
        validatePATCH(userId);
        validatePUT(userId);
        validateGETBY(userId);
        validateDELETE(userId);
    }

    private void validateGET(String userId) {}

    private String validatePOST() {
        CreateUserRequest request = ModelFactory.getCreateUserRequest();
        ResponseEntity<CreateUser201Response> responseEntity =
                restTemplate.postForEntity(getUrl(), request, CreateUser201Response.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCode().value());
        CreateUser201Response response = responseEntity.getBody();
        assertNotNull(response);
        String userId = response.getId();

        User user = ModelFactory.getUser(response.getId());
        UserEntity entity = databaseUtils.find(userId);
        assertEquals(ModelFactory.LAST_NAME, entity.getLastName());
        assertEquals(ModelFactory.EMAIL, entity.getEmail());

        kafkaConsumerUtils.validateMessageCreate(user);
        kafkaConsumerUtils.validateIsEmpty();

        return user.getId();
    }

    private void validatePATCH(String userId) {
        CreateUserRequest request = ModelFactory.getCreateUserRequestForPatch();
        UserModel response = restTemplate.patchForObject(getUrl(userId), request, UserModel.class);

        assertNotNull(response);
        assertEquals(ModelFactory.LAST_NAME_PATCH, response.getLastName());
        assertEquals(ModelFactory.EMAIL, response.getEmail());

        User user = ModelFactory.getUser(userId).withLastName(ModelFactory.LAST_NAME_PATCH);
        UserEntity entity = databaseUtils.find(userId);
        assertEquals(ModelFactory.LAST_NAME_PATCH, entity.getLastName());
        assertEquals(ModelFactory.EMAIL, entity.getEmail());

        kafkaConsumerUtils.validateMessageUpdate(user);
        kafkaConsumerUtils.validateIsEmpty();
    }

    private void validatePUT(String userId) {
        //        CreateUserRequest request = ModelFactory.getCreateUserRequestForPut();
        //        UserModel response = restTemplate.put(getUrl(userId), request, UserModel.class);
        //
        //        assertNotNull(response);
        //        assertEquals(ModelFactory.LAST_NAME_PUT, response.getLastName());
        //        assertNull(response.getEmail());
        //
        //        User user =
        //                ModelFactory.getUser(userId)
        //                        .withLastName(ModelFactory.LAST_NAME_PUT)
        //                        .withEmail(null);
        //        UserEntity entity = databaseUtils.find(userId);
        //        assertEquals(ModelFactory.LAST_NAME_PUT, entity.getLastName());
        //        assertNull(entity.getEmail());
        //
        //        kafkaConsumerUtils.validateMessageUpdate(user);
        //        kafkaConsumerUtils.validateIsEmpty();
    }

    private void validateGETBY(String userId) {}

    private void validateDELETE(String userId) {}

    private String getUrl(String userId) {
        return getUrl() + "/" + userId;
    }

    private String getUrl() {
        return "http://localhost:" + port + "/user";
    }
}
