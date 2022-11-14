package helpers;

import config.ProjectConfiguration;
import config.TestConfig;
import io.qameta.allure.restassured.AllureRestAssured;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

    public static String videoUrl(String sessionId) {
        TestConfig config = ProjectConfiguration.TEST_CONFIG;
        String url = format("%s/%s.json", config.browserstackSessionsUrl(), sessionId);

        return given()
                .filter(new AllureRestAssured())
                .log().all()
                .auth().basic(config.browserstackUserName(), config.browserstackPassword())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}