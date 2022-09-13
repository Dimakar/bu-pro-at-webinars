package org.example.test.ui;

import org.example.dto.PhoneDto;
import org.example.extensions.UiTest;
import org.example.pages.ProductListPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;
import static org.example.testdata.TestDataManager.getAllPhones;

@UiTest
public class FiltersTest {

    static List<PhoneDto> phoneDtoList;

    @BeforeAll
    static void beforeAll() {
        phoneDtoList = getAllPhones();
    }

    public static Stream<Arguments> filters() {
        return Stream.of(Arguments.of("Price", List.of("<250"), phoneDtoList.stream()
                        .filter(phone -> phone.getInfo().getPrice() < 250L).collect(Collectors.toList())),
                Arguments.of("Color", List.of("black", "white"), phoneDtoList.stream()
                        .filter(phone -> phone.getInfo().getColor().toLowerCase(Locale.ROOT).contains("black")
                                || phone.getInfo().getColor().toLowerCase(Locale.ROOT).contains("white"))
                        .collect(Collectors.toList()))
        );
    }

    @ParameterizedTest
    @MethodSource("filters")
    void filterByOneParameterTest(String parameter, List<String> filterValues, List<PhoneDto> phoneList) {
        open("");
        new ProductListPage()
                .openFilter(parameter)
                .selectFiltersValues(filterValues)
                .checkProductList(phoneList);
    }
}
