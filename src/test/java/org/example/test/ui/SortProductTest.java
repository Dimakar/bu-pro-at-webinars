package org.example.test.ui;

import org.example.dto.PhoneDto;
import org.example.extensions.UiTest;
import org.example.pages.ProductListPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.steps.UiSteps.openPage;
import static org.example.testdata.TestDataManager.getAllPhones;

@UiTest
public class SortProductTest {
    static List<PhoneDto> phoneDtoList;

    @BeforeAll
    static void beforeAll() {
        phoneDtoList = getAllPhones();
    }

    public static Stream<Arguments> sortings() {
        return Stream.of(Arguments.of("Name: A-Z", phoneDtoList.stream()
                        .sorted((p1, p2) -> p1.getInfo().getName().compareToIgnoreCase(p2.getInfo().getName()))
                        .collect(Collectors.toList())),
                Arguments.of("Name: Z-A", phoneDtoList.stream()
                        .sorted((p1, p2) -> p2.getInfo().getName().compareToIgnoreCase(p1.getInfo().getName()))
                        .collect(Collectors.toList())),
                Arguments.of("Price: Low to High", phoneDtoList.stream()
                        .sorted((p1, p2) -> (int) (p1.getInfo().getPrice() - p2.getInfo().getPrice()))
                        .collect(Collectors.toList())),
                Arguments.of("Price: High to Low", phoneDtoList.stream()
                        .sorted((p1, p2) -> (int) (p2.getInfo().getPrice() - p1.getInfo().getPrice()))
                        .collect(Collectors.toList()))
        );
    }

    @ParameterizedTest
    @MethodSource("sortings")
    void checkSortingTest(String sortingName, List<PhoneDto> phoneDtoList) {
        openPage(ProductListPage.class)
                .selectSorting(sortingName)
                .checkProductList(phoneDtoList);
    }
}
