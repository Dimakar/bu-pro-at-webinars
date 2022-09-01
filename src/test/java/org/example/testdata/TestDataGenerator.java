package org.example.testdata;

import com.github.javafaker.Faker;
import org.example.dto.CreateUserRequestDto;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

    public static CreateUserRequestDto generateUserDto() {
        return CreateUserRequestDto.builder()
                .userName(faker.name().lastName() + faker.number().numberBetween(0, 9))
                .address(faker.address().fullAddress())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .build();
    }
}
