package org.example.test.api;

import com.github.javafaker.Faker;
import io.restassured.http.Header;
import org.example.dto.*;
import org.example.extensions.ApiTestExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("/api/order")
@ExtendWith(ApiTestExtension.class)
public class ApiNewOrderTest {

    CreateUserResponseDto user;
    PhoneDto phone;

    @BeforeEach
    void setUp() {
        Faker faker = new Faker();
        phone = List.of(given()
                .get("/api/catalog")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(PhoneDto[].class)).get(faker.number().numberBetween(0, 9));

        user = given()
                .body(CreateUserRequestDto.builder()
                        .userName(faker.name().lastName())
                        .address(faker.address().fullAddress())
                        .email(faker.internet().emailAddress())
                        .password(faker.internet().password())
                        .phoneNumber(faker.phoneNumber().phoneNumber())
                        .build())
                .post("/api/auth/register")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .as(CreateUserResponseDto.class);
    }

    @Test
    void apiNewOrderTest() {
        Order order = Order.builder()
                .dateCreated(LocalDateTime.now().withNano(0))
                .name(phone.getInfo().getName())
                .price(phone.getInfo().getPrice())
                .quantity(1)
                .build();

        given()
                .header(new Header("Authorization", "Bearer " + user.getToken()))
                .body(OrderRequestDto.builder().order(order).build())
                .post("/api/order")
                .then()
                .statusCode(200);

        CreateUserResponseDto userResponseDto = given()
                .header(new Header("Authorization", "Bearer " + user.getToken()))
                .get("/api/user")
                .then()
                .statusCode(200)
                .extract()
                .as(CreateUserResponseDto.class);

        assertThat(userResponseDto.getOrders())
                .containsExactlyInAnyOrder(order);
    }
}
