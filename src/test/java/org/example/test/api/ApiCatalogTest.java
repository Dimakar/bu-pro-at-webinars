package org.example.test.api;

import org.example.db.model.ProductModel;
import org.example.dto.PhoneDto;
import org.example.endpoints.ApiCatalogEndpoint;
import org.example.extensions.ApiTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.db.MongoConnector.getDataBase;

@DisplayName("/api/catalog")
@ApiTest
public class ApiCatalogTest {
    List<ProductModel> expectedPhoneList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        getDataBase().getCollection("products", ProductModel.class)
                .find().into(expectedPhoneList);
    }

    @DisplayName("/api/catalog")
    @Test
    void apiCatalogTest() {
        List<PhoneDto> actualResult = new ApiCatalogEndpoint().getProducts();
        assertThat(actualResult).hasSize(expectedPhoneList.size());
        for (int i = 0; i < actualResult.size(); i++) {
            assertThat(actualResult.get(i))
                    .usingRecursiveComparison()
                    .isEqualTo(expectedPhoneList.get(i));
        }
    }
}
