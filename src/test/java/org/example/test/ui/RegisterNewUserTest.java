package org.example.test.ui;

import com.github.javafaker.Faker;
import org.example.dto.CreateUserRequestDto;
import org.example.extensions.UITestExtension;
import org.example.pages.ProductListPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

@DisplayName("Регистрация юзеров")
@ExtendWith(UITestExtension.class)
public class RegisterNewUserTest {

    public static Stream<CreateUserRequestDto> testDataUser() {
        Faker faker = new Faker();
        return Stream.of(CreateUserRequestDto.builder()
                        .userName(faker.name().lastName())
                        .address(faker.address().fullAddress())
                        .email(faker.internet().emailAddress())
                        .password(faker.internet().password())
                        .phoneNumber(faker.phoneNumber().phoneNumber())
                        .build(),
                CreateUserRequestDto.builder()
                        .userName(faker.name().lastName())
                        .password(faker.internet().password())
                        .build()
        );
    }

    @DisplayName("Создание нового юзера")
    @ParameterizedTest
    @MethodSource("testDataUser")
    void registerNewUserTest(CreateUserRequestDto createUserRequestDto) {
        open("");

        new ProductListPage()
                .getHeaderElement()
                .clickLoginButton()
                .clickRegisterHere()
                .inputNewUserData(createUserRequestDto)
                .submit()
                .getHeaderElement()
                .clickAccountButton()
                .checkUserData(createUserRequestDto);
    }
}
