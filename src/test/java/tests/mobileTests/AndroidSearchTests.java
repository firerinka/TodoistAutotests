package tests.mobileTests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AndroidSearchTests extends TestBase {

    @Test
    void searchTest() {
        step("Close onboarding screen", () -> back());
        step("Type search", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    void searchWithByTextLocatorTest() {
        step("Close onboarding screen", () -> back());
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    void onboardingSuccessfullyPassedTest() {
        step("Checking first onboarding screen", () -> {
            checkTextAndPicture("The Free Encyclopedia\n" +
                    "…in over 300 languages");
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Checking second onboarding screen", () -> {
            checkTextAndPicture("New ways to explore");
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Checking third onboarding screen", () -> {
            checkTextAndPicture("Reading lists with sync");
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Checking last onboarding screen", () -> {
            checkTextAndPicture("Send anonymous data");
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();
        });

        step("Checking onboarding is finished", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/main_toolbar"))
                    .shouldBe(Condition.visible);
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container"))
                    .shouldBe(Condition.visible);
            $(AppiumBy.id("org.wikipedia.alpha:id/main_nav_tab_container"))
                    .shouldBe(Condition.visible);
        });
    }

    private void checkTextAndPicture(String text) {
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(Condition.text(text));
        $(AppiumBy.id("org.wikipedia.alpha:id/imageViewCentered"))
                .shouldBe(Condition.visible);
    }
}