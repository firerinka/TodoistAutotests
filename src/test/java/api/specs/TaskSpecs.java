package api.specs;

import config.ProjectConfiguration;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class TaskSpecs {
    public static RequestSpecification taskRequestSpec = with()
            .filter(withCustomTemplates())
            .basePath("tasks")
            .header("Authorization", "Bearer " + ProjectConfiguration.getApiToken())
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static ResponseSpecification taskResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();

    public static ResponseSpecification errorResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();


    public static ResponseSpecification taskDeletionResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
