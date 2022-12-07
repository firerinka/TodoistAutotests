package api.steps;

import api.models.requests.SyncArgs;
import api.models.requests.UserUpdateRequest;
import config.ProjectConfiguration;
import io.qameta.allure.Step;

import java.util.UUID;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;

public class UserSteps {
    @Step("API: Устанавливаем для пользователя таймзону '{timezoneValue}'")
    public static void setTimezone(String timezoneValue) {
        UserUpdateRequest timezone = new UserUpdateRequest();
        timezone.setType("user_update");
        timezone.setUuid(UUID.randomUUID().toString());
        SyncArgs args = new SyncArgs();
        args.setTimezone(timezoneValue);
        timezone.setArgs(args);

        given()
                .filter(withCustomTemplates())
                .baseUri(ProjectConfiguration.TEST_CONFIG.syncApiUrl())
                .header("Authorization", "Bearer " + ProjectConfiguration.getApiToken())
                .multiPart("commands", asList(timezone))
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(200);
    }

}
