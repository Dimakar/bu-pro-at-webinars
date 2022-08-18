package org.example;

import com.github.javafaker.Faker;
import org.example.dto.UserData;
import org.example.pages.ProductListPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Регистрация юзеров")
public class RegisterNewUserTest extends BaseTest {

    public static Stream<UserData> testDataUser() {
        Faker faker = new Faker();
        return Stream.of(UserData.builder()
                        .userName(faker.name().lastName())
                        .address(faker.address().fullAddress())
                        .email(faker.internet().emailAddress())
                        .password(faker.internet().password())
                        .phoneNumber(faker.phoneNumber().phoneNumber())
                        .build(),
                UserData.builder()
                        .userName(faker.name().lastName())
                        .password(faker.internet().password())
                        .build()
        );
    }

    @DisplayName("Создание нового юзера")
    @ParameterizedTest
    @MethodSource("testDataUser")
    void registerNewUserTest(UserData userData) {
        webDriver.get("http://192.168.235.13:3000/");

        new ProductListPage(webDriver)
                .getHeaderElement()
                .clickLoginButton()
                .clickRegisterHere()
                .inputNewUserData(userData)
                .submit()
                .getHeaderElement()
                .clickAccountButton()
                .checkUserData(userData);
    }
}
