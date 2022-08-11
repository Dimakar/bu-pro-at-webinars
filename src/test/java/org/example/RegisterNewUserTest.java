package org.example;

import com.github.javafaker.Faker;
import org.example.pages.ProductListPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class RegisterNewUserTest extends BaseTest {

    public static Stream<Arguments> testDataUser() {
        Faker faker = new Faker();
        return Stream.of(Arguments.of(faker.name().lastName(), faker.internet().password(), faker.internet().emailAddress(), faker.address().fullAddress(), faker.phoneNumber().phoneNumber()));
    }

    @DisplayName("Создание нового юзера")
    @ParameterizedTest
    @MethodSource("testDataUser")
    void registerNewUserTest(String userName, String password, String email, String address, String phoneNumber) {
        webDriver.get("http://192.168.235.13:3000/");

        new ProductListPage(webDriver)
                .getHeaderElement()
                .clickLoginButton()
                .clickRegisterHere()
                .inputNewUserData(userName, password, email, address, phoneNumber) // TODO: 11.08.2022 избавиться от кучи параметров
                .submit()
                .getHeaderElement()
                .clickAccountButton()
                .checkUserData(userName, email, address, phoneNumber);
    }
}
