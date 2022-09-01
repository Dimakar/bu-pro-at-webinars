package org.example.endpoints;

import io.qameta.allure.Step;
import org.example.dto.CreateUserRequestDto;
import org.example.dto.CreateUserResponseDto;
import org.example.dto.PhoneDto;

import java.util.List;

import static io.restassured.RestAssured.given;

@Endpoint(uri = "/api/catalog")
public class ApiCatalogEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: Получение всех продуктов")
    public List<PhoneDto> getProducts() {
        return List.of(given()
                .get("/api/catalog")
                .then()
                .statusCode(200)
                .extract()
                .as(PhoneDto[].class));
    }
}
