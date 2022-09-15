package org.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.example.dto.Info;
import org.example.dto.PhoneDto;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

@Page(url = "/")
public class ProductListPage extends BasePage {

    SelenideElement filterList = $x("//div[@class='filtersList-desktop']");

    @Step("Выбрать продукт {productName}")
    public ProductPage selectProduct(String productName) {
        $$(By.className("product"))
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable()
                .stream()
                .filter(product -> product.getText().contains(productName))
                .findFirst()
                .orElseThrow()
                .$x(".//*[text()='See more']")
                .click();
        return new ProductPage();
    }

    public ProductListPage openFilters(List<String> filterNames) {
        filterNames.forEach(filterName ->
                filterList.$x(".//div[text()='" + filterName + "']").click());
        return this;
    }

    public ProductListPage selectFiltersValues(List<String> filterValues) {
        filterValues.forEach(value ->
                filterList.$x(".//input[@type='checkbox' and @value ='" + value + "']").click());
        return new ProductListPage();
    }

    public ProductListPage checkProductList(List<PhoneDto> phoneList) {

        List<PhoneDto> actualList = $$(By.className("product"))
                .shouldHave(CollectionCondition.size(phoneList.size()))
                .asDynamicIterable()
                .stream()
                .map(product -> PhoneDto.builder()
                        .info(Info.builder()
                                .name(product.$x(".//h3").getText())
                                .price(Double.parseDouble(product.$x(".//h2").getText()
                                        .replaceAll("\\$", "")))
                                .displaySize(product.$x(".//div[./*[text()='Display size: ']]/span").getText())
                                .build())
                        .build())
                .collect(Collectors.toList());

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < actualList.size(); i++) {
            softAssertions.assertThat(actualList.get(i))
                    .usingRecursiveComparison()
                    .comparingOnlyFields("info.name", "info.price")
                    .isEqualTo(phoneList.get(i));
        }
        softAssertions.assertAll();
        return this;
    }

    public ProductListPage selectSorting(String sortingName) {
        $x("//div[@class='sort-field']//button").click();
        $x("//span[@role='menuitem' and .='" + sortingName + "']").click();
        return new ProductListPage();
    }
}
