package org.example.test.api;

import io.qameta.allure.junit5.AllureJunit5;
import org.example.dto.PhoneDto;
import org.example.endpoints.ApiCatalogEndpoint;
import org.example.extensions.ApiTestExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.db.MongoConnector.getDataBase;

@DisplayName("/api/catalog")
@ExtendWith({ApiTestExtension.class, AllureJunit5.class})
public class ApiCatalogTest {

    List<PhoneDto> expectedPhoneList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        getDataBase().getCollection("products", PhoneDto.class)
                .find().into(expectedPhoneList);
    }

    @DisplayName("/api/catalog")
    @Test
    void apiCatalogTest() {
        List<PhoneDto> actualResult = new ApiCatalogEndpoint().getProducts();
        assertThat(actualResult)
                .containsExactlyInAnyOrderElementsOf(expectedPhoneList); // TODO: 06.09.2022 how check using recursive corp
    }
}
