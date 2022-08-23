package org.example.test.api;

import com.github.javafaker.Faker;
import org.example.dto.UserData;
import org.example.extensions.ApiTestExtension;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

@DisplayName("/api/auth/register")
@ExtendWith(ApiTestExtension.class)
public class ApiUserRegisterTest {

    public static Stream<UserData> testDataUser() {
        Faker faker = new Faker();
        return Stream.of(UserData.builder()
                        .userName(faker.name().lastName())
                        .address(faker.address().fullAddress())
                        .email(faker.internet().emailAddress())
                        .password(faker.internet().password())
                        .phoneNumber(faker.phoneNumber().phoneNumber())
                        .build()
//                UserData.builder()
//                        .userName(faker.name().lastName())
//                        .password(faker.internet().password())
//                        .build()
        );
    }

    @DisplayName("/api/auth/register")
    @ParameterizedTest()
    @MethodSource("testDataUser")
    void apiUserRegisterTest(UserData userData) {
        given()
                // TODO: 25.08.2022 переписать, чтобы можно было отправлять объект
                .body("{\n" +
                        "  \"address\": \"" + userData.getAddress() + "\",\n" +
                        "  \"email\": \"" + userData.getEmail() + "\",\n" +
                        "  \"password\": \"" + userData.getPassword() + "\",\n" +
                        "  \"phone\": \"" + userData.getPhoneNumber() + "\",\n" +
                        "  \"username\": \"" + userData.getUserName() + "\"\n" +
                        "}")
                .post("/api/auth/register")
                .then()
                .statusCode(201)
                .body("address", Matchers.equalTo(userData.getAddress()))
                .body("email", Matchers.equalTo(userData.getEmail()))
                .body("phone", Matchers.equalTo(userData.getPhoneNumber()))
                .body("username", Matchers.equalTo(userData.getUserName()));
    }
}
