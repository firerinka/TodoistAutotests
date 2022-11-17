package api.specs;

import config.ProjectConfiguration;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class NewTaskSpecs {
    public static RequestSpecification newTaskRequestSpec = with()
            .filter(withCustomTemplates())
            .basePath("tasks")
            .header("Authorization", "Bearer " + ProjectConfiguration.getApiToken())
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static ResponseSpecification newTaskResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();

    public static ResponseSpecification errorResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
