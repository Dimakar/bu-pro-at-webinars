package org.example.test.api;

import org.example.extensions.ApiTestExtension;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;

@DisplayName("/api/catalog")
@ExtendWith(ApiTestExtension.class)
public class ApiCatalogTest {

    @DisplayName("/api/catalog")
    @Test
    void apiCatalogTest() {
        given()
                .get("/api/catalog")
                .then()
                .statusCode(200)
                .body("info.name", Matchers.hasItems("Apple iPhone 8 Plus",
                        "Samsung Galaxy S8", "Huawei P10"));
    }
}
