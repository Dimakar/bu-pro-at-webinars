package org.example.endpoints;

import io.qameta.allure.Step;
import org.example.dto.Order;
import org.example.dto.OrderRequestDto;
import org.example.testdata.User;

import static io.restassured.RestAssured.given;

@Endpoint(uri = "/api/order")
public class ApiOrderEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: Оформление заказа")
    public void createOrder(User user, Order order) {
        given()
                .header(user.getTokenHeader())
                .body(OrderRequestDto.builder().order(order).build())
                .post(endpoint)
                .then()
                .statusCode(200);
    }
}
