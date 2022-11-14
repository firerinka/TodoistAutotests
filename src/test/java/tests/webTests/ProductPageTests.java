package tests.webTests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ProductPageTests extends TestBase {
    private final String URL_PART = "products/";

    @Test
    @DisplayName("Главное меню содержит верный набор элементов")
    public void headerMenuHasCorrectItemsTest() {
        List<String> expectedItems = List.of("Разработчикам",
                "Командам",
                "Для обучения",
                "Решения",
                "Поддержка",
                "Магазин");

        step("Открываем страницу с продуктами https://www.jetbrains.com/ru-ru/products/", () -> {
            open(URL_PART);
        });
        step("Проверяем состав элементов главного меню", () -> {
            $$("[data-test=main-menu-item-action]")
                    .shouldHave(CollectionCondition.texts(expectedItems));
        });
    }

    @Test
    @DisplayName("На странице продуктов отображаются карточки при выключенных фильтрах")
    public void productPageWithoutFiltersHasProductCardsTest() {
        step("Открываем страницу с продуктами https://www.jetbrains.com/ru-ru/products/", () -> {
            open(URL_PART);
        });
        step("Проверяем, что на странице отображаются карточки продуктов", () -> {
            $$("[data-test=cardSection]").shouldHave(CollectionCondition.sizeGreaterThan(0));
        });
    }

    @Test
    @DisplayName("По клику на карточку продукта происходит переход на страницу продукта")
    public void clickOnProductCardOpensProductPageTest() {
        final String PRODUCT_URL_PART = "idea/";
        final String PRODUCT_NAME = "IntelliJ IDEA";

        step("Открываем страницу с продуктами https://www.jetbrains.com/ru-ru/products/", () -> {
            open(URL_PART);
        });

        step("Кликаем на карточку продукта " + PRODUCT_NAME, () -> {
            $$("[data-test=cardSection] a")
                    .filter(Condition.exactText(PRODUCT_NAME))
                    .first()
                    .click();
        });

        step("Проверяем, что произошел переход на страницу продукта " + PRODUCT_NAME, () -> {
            Assertions.assertTrue(Selenide.webdriver().driver().url().contains(Configuration.baseUrl + PRODUCT_URL_PART));

        });
    }

    @Test
    @DisplayName("Поиск по языкам ищет элементы фильтра по названию")
    public void languageSearchFilteringElementsByNameTest() {
        final String LANGUAGE_NAME = "Java";

        step("Открываем страницу с продуктами https://www.jetbrains.com/ru-ru/products/", () -> {
            open(URL_PART);
        });

        SelenideElement item = $$("[data-test=accordion] [data-test=accordion-item]").first();

        step("Вводим в фильтр пр языкам значение " + LANGUAGE_NAME, () -> {
            item.$("input").setValue(LANGUAGE_NAME);
        });

        step("Проверяем, что в списке языков в панели фильров есть язык " + LANGUAGE_NAME, () -> {
            item.$$(".filter__list label")
                    .filter(visible)
                    .shouldHave(itemWithText(LANGUAGE_NAME));
        });
    }

    @Test
    @DisplayName("Поле поиска фильтров очищается по нажатию на Х")
    public void searchFiltersFieldIsCleanedByX() {
        step("Открываем страницу с продуктами https://www.jetbrains.com/ru-ru/products/", () -> {
            open(URL_PART);
        });

        SelenideElement item =
                $$("[data-test=accordion] [data-test=accordion-item] [class*=accordion-item__content-container]").first();
        SelenideElement input = item.$("input");

        step("Вводим текст в поле фильтра по языкам", () -> {
            input.setValue("Java");
        });

        step("Вводим текст в поле фильтра по языкам", () -> {
            input.setValue("Java");
        });

        SelenideElement clearingIcon = item.$("svg");

        step("Проверяем наличие крестика в поле ввода и кликаем на него", () -> {
            clearingIcon.shouldBe(visible);
            clearingIcon.click();
        });

        step("Проверяем, что поле ввода очищено", () -> {
            input.shouldBe(empty);
        });
    }

}
