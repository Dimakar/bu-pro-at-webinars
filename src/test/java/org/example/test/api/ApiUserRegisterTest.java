package org.example.test.api;

import com.github.javafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.example.dto.CreateUserRequestDto;
import org.example.dto.CreateUserResponseDto;
import org.example.endpoints.ApiUserRegisterEndpoint;
import org.example.extensions.ApiTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.modelmapper.ModelMapper;

import java.util.stream.Stream;

import static org.example.testdata.TestDataManager.deleteUser;

@DisplayName("/api/auth/register")
@ApiTest
public class ApiUserRegisterTest {
    CreateUserResponseDto responseDto;

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
        responseDto = new ApiUserRegisterEndpoint().registerUser(createUserRequestDto);

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

    @AfterEach
    void tearDown() {
        deleteUser(responseDto.getUsername());
    }

    // TODO: 01.09.2022 add negative tests
}
