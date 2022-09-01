package org.example.test.api;

import com.github.javafaker.Faker;
import io.qameta.allure.junit5.AllureJunit5;
import org.assertj.core.api.SoftAssertions;
import org.example.dto.CreateUserRequestDto;
import org.example.dto.CreateUserResponseDto;
import org.example.endpoints.ApiUserRegisterEndpoint;
import org.example.extensions.ApiTestExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.modelmapper.ModelMapper;

import java.util.stream.Stream;

@DisplayName("/api/auth/register")
@ExtendWith({ApiTestExtension.class, AllureJunit5.class})
public class ApiUserRegisterTest {

    public static Stream<CreateUserRequestDto> testDataUser() {
        Faker faker = new Faker();
        return Stream.of(CreateUserRequestDto.builder()
                        .userName(faker.name().lastName() + faker.number().numberBetween(0, 9))
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

    @DisplayName("/api/auth/register")
    @ParameterizedTest()
    @MethodSource("testDataUser")
    void apiUserRegisterTest(CreateUserRequestDto createUserRequestDto) {
        CreateUserResponseDto responseDto = new ApiUserRegisterEndpoint().registerUser(createUserRequestDto);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(responseDto)
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .ignoringFields("password")
                .isEqualTo(new ModelMapper().map(createUserRequestDto, CreateUserResponseDto.class));
        softAssertions.assertThat(responseDto.getOrders()).isEmpty();
        softAssertions.assertThat(responseDto.getToken()).isNotEmpty();
        softAssertions.assertThat(responseDto.getId()).isNotEmpty();
        softAssertions.assertThat(responseDto.getPassword()).isNotEmpty();

        softAssertions.assertAll();
    }

    // TODO: 01.09.2022 add negative tests
}
