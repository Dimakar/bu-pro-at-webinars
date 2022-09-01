package org.example.endpoints;

import io.qameta.allure.Step;
import org.example.dto.CreateUserRequestDto;
import org.example.dto.CreateUserResponseDto;
import org.example.testdata.User;

import static io.restassured.RestAssured.given;

@Endpoint(uri = "/api/user")
public class ApiUserEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: Получение информации об юзере")
    public CreateUserResponseDto getUser(User user) {
        return given()
                .header(user.getTokenHeader())
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateUserResponseDto.class);
    }
}
