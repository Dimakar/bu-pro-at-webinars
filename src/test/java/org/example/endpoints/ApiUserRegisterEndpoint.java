package org.example.endpoints;

import io.qameta.allure.Step;
import org.example.dto.CreateUserRequestDto;
import org.example.dto.CreateUserResponseDto;

import static io.restassured.RestAssured.given;

@Endpoint(uri = "/api/auth/register")
public class ApiUserRegisterEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: Регистрация нового юзера")
    public CreateUserResponseDto registerUser(CreateUserRequestDto createUserRequestDto) {
        return given()
                .body(createUserRequestDto)
                .post(endpoint)
                .then()
                .statusCode(201)
                .extract()
                .body()
                .as(CreateUserResponseDto.class);
    }
}
