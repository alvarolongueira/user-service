package com.alvarolongueira.user.service;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.infrastructure.database.repository.entity.UserEntity;
import com.alvarolongueira.user.service.kafka.KafkaConsumerUtils;
import com.alvarolongueira.user.service.rest.api.model.CreateUser201Response;
import com.alvarolongueira.user.service.rest.api.model.CreateUserRequest;
import com.alvarolongueira.user.service.rest.api.model.GetUsersBy200Response;
import com.alvarolongueira.user.service.rest.api.model.UserModel;
import com.alvarolongueira.user.service.testcontainer.AbstractTestContainer;
import com.alvarolongueira.user.service.utils.DatabaseUtils;
import com.alvarolongueira.user.service.utils.ModelFactory;

import io.restassured.http.ContentType;

import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
class UserIntegrationTest extends AbstractTestContainer {

    @Autowired private KafkaConsumerUtils kafkaConsumerUtils;
    @Autowired private DatabaseUtils databaseUtils;

    @Test()
    void allApisWorkAsExpected() {
        String userId = validatePOST();
        validateGET(userId);
        validateGETBY(userId);
        validatePATCH(userId);
        validatePUT(userId);
        validateDELETE(userId);
    }

    private String validatePOST() {
        CreateUserRequest request = ModelFactory.getCreateUserRequest();
        CreateUser201Response response =
                given().contentType(ContentType.JSON)
                        .body(request)
                        .when()
                        .post(USER_PATH)
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .response()
                        .as(CreateUser201Response.class);

        assertNotNull(response);
        String userId = response.getId();

        User user = ModelFactory.getUser(response.getId());
        UserEntity entity = databaseUtils.find(userId);
        assertEquals(ModelFactory.LAST_NAME, entity.getLastName());
        assertEquals(ModelFactory.COUNTRY, entity.getCountry());

        kafkaConsumerUtils.validateMessageCreate(user);
        kafkaConsumerUtils.validateIsEmpty();

        return user.getId();
    }

    private void validateGET(String userId) {
        UserModel response =
                given().contentType(ContentType.JSON)
                        .when()
                        .get(getUserWithIdPath(userId))
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .response()
                        .as(UserModel.class);

        assertNotNull(response);
        assertEquals(ModelFactory.LAST_NAME, response.getLastName());
        assertEquals(ModelFactory.COUNTRY, response.getCountry());

        UserEntity entity = databaseUtils.find(userId);
        assertEquals(ModelFactory.LAST_NAME, entity.getLastName());
        assertEquals(ModelFactory.COUNTRY, entity.getCountry());
    }

    private void validateGETBY(String userId) {
        String path = USER_PATH + "?page=0&size=5&first_name=" + ModelFactory.FIRST_NAME;
        GetUsersBy200Response response =
                given().contentType(ContentType.JSON)
                        .when()
                        .get(path)
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .response()
                        .as(GetUsersBy200Response.class);

        assertNotNull(response);
        assertEquals(0, response.getPageNumber());
        assertEquals(5, response.getSize());
        assertEquals(1, response.getTotalElements());
        assertEquals(1, response.getTotalPages());

        assertTrue(CollectionUtils.isNotEmpty(response.getContent()));
        UserModel user = response.getContent().get(0);
        assertEquals(ModelFactory.LAST_NAME, user.getLastName());
        assertEquals(ModelFactory.COUNTRY, user.getCountry());

        UserEntity entity = databaseUtils.find(userId);
        assertEquals(ModelFactory.LAST_NAME, entity.getLastName());
        assertEquals(ModelFactory.COUNTRY, entity.getCountry());
    }

    private void validatePATCH(String userId) {
        CreateUserRequest request = ModelFactory.getCreateUserRequestForPatch();
        UserModel response =
                given().contentType(ContentType.JSON)
                        .body(request)
                        .when()
                        .patch(getUserWithIdPath(userId))
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .response()
                        .as(UserModel.class);

        assertNotNull(response);
        assertEquals(ModelFactory.LAST_NAME_PATCH, response.getLastName());
        assertEquals(ModelFactory.COUNTRY, response.getCountry());

        User user = ModelFactory.getUser(userId).withLastName(ModelFactory.LAST_NAME_PATCH);
        UserEntity entity = databaseUtils.find(userId);
        assertEquals(ModelFactory.LAST_NAME_PATCH, entity.getLastName());
        assertEquals(ModelFactory.COUNTRY, entity.getCountry());

        kafkaConsumerUtils.validateMessageUpdate(user);
        kafkaConsumerUtils.validateIsEmpty();
    }

    private void validatePUT(String userId) {
        CreateUserRequest request = ModelFactory.getCreateUserRequestForPut();
        UserModel response =
                given().contentType(ContentType.JSON)
                        .body(request)
                        .when()
                        .put(getUserWithIdPath(userId))
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .response()
                        .as(UserModel.class);

        assertNotNull(response);
        assertEquals(ModelFactory.LAST_NAME_PUT, response.getLastName());
        assertNull(response.getCountry());

        User user =
                ModelFactory.getUser(userId)
                        .withLastName(ModelFactory.LAST_NAME_PUT)
                        .withCountry(null);
        UserEntity entity = databaseUtils.find(userId);
        assertEquals(ModelFactory.LAST_NAME_PUT, entity.getLastName());
        assertNull(entity.getCountry());

        kafkaConsumerUtils.validateMessageUpdate(user);
        kafkaConsumerUtils.validateIsEmpty();
    }

    private void validateDELETE(String userId) {
        CreateUserRequest request = ModelFactory.getCreateUserRequestForPut();
        given().contentType(ContentType.JSON)
                .body(request)
                .when()
                .delete(getUserWithIdPath(userId))
                .then()
                .statusCode(HttpStatus.OK.value());

        User user = ModelFactory.getUser(userId);
        databaseUtils.noExists(userId);

        kafkaConsumerUtils.validateMessageDelete(user);
        kafkaConsumerUtils.validateIsEmpty();
    }
}
