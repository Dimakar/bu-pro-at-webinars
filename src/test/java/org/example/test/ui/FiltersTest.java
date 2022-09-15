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
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.steps.UiSteps.openPage;
import static org.example.testdata.TestDataManager.getAllPhones;

@UiTest
public class FiltersTest {

    static List<PhoneDto> phoneDtoList;

    static Predicate<PhoneDto> filterUnder500Dollars = phoneDto -> phoneDto.getInfo().getPrice() < 500L;
    static Predicate<PhoneDto> blackAndWhiteFilters = phone -> phone.getInfo().getColor().toLowerCase(Locale.ROOT).contains("black")
            || phone.getInfo().getColor().toLowerCase(Locale.ROOT).contains("white");

    @BeforeAll
    static void beforeAll() {
        phoneDtoList = getAllPhones();
    }

    public static Stream<Arguments> filters() {
        return Stream.of(Arguments.of(List.of("Price"), List.of("<250", "250-500"), phoneDtoList.stream()
                        .filter(filterUnder500Dollars)
                        .collect(Collectors.toList())),
                Arguments.of(List.of("Color"), List.of("black", "white"), phoneDtoList.stream()
                        .filter(blackAndWhiteFilters)
                        .collect(Collectors.toList())),
                Arguments.of(List.of("Price", "Color"), List.of("<250", "250-500", "black", "white"), phoneDtoList.stream()
                        .filter(filterUnder500Dollars)
                        .filter(blackAndWhiteFilters)
                        .collect(Collectors.toList()))
        );
    }

    @ParameterizedTest
    @MethodSource("filters")
    void filterByOneParameterTest(List<String> parameter, List<String> filterValues, List<PhoneDto> phoneList) {
        openPage(ProductListPage.class)
                .openFilters(parameter)
                .selectFiltersValues(filterValues)
                .checkProductList(phoneList);
    }
}
