package org.example.test.api;

import com.github.javafaker.Faker;
import io.qameta.allure.junit5.AllureJunit5;
import org.example.dto.Order;
import org.example.dto.PhoneDto;
import org.example.endpoints.ApiCatalogEndpoint;
import org.example.endpoints.ApiOrderEndpoint;
import org.example.endpoints.ApiUserEndpoint;
import org.example.extensions.ApiTestExtension;
import org.example.testdata.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.testdata.TestDataManager.createNewUser;
import static org.example.testdata.TestDataManager.deleteUser;

@DisplayName("/api/order")
@ExtendWith({ApiTestExtension.class, AllureJunit5.class})
public class ApiNewOrderTest {

    static User user;
    static PhoneDto phone;

    List<Order> startOrders;

    public static Stream<Order> orders() {
        return Stream.of(Order.builder()
                        .dateCreated(LocalDateTime.now().withNano(0))
                        .name(phone.getInfo().getName())
                        .price(phone.getInfo().getPrice())
                        .quantity(1)
                        .build(),
                Order.builder()
                        .dateCreated(LocalDateTime.now().withNano(0))
                        .name(phone.getInfo().getName())
                        .price(phone.getInfo().getPrice())
                        .quantity(2)
                        .build()
        );
    }

    @BeforeAll
    static void beforeAll() {
        Faker faker = new Faker();
        phone = new ApiCatalogEndpoint().getProducts().get(faker.number().numberBetween(0, 9));
        user = createNewUser();
    }

    @BeforeEach
    void setUp() {
        startOrders = new ApiUserEndpoint().getUser(user).getOrders();
    }

    @ParameterizedTest
    @MethodSource("orders")
    void apiNewOrderTest(Order order) {
        new ApiOrderEndpoint().createOrder(user, order);

        List<Order> endOrders = new ApiUserEndpoint().getUser(user).getOrders();

        startOrders.add(order);
        assertThat(endOrders)
                .containsExactlyInAnyOrderElementsOf(startOrders);
    }

    @AfterAll
    static void tearDown() {
        deleteUser(user);
    }
}
