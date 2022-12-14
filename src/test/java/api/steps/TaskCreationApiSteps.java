package api.steps;

import api.models.requests.TaskRequest;
import api.models.responses.TaskResponse;
import io.qameta.allure.Step;

import static api.specs.TaskSpecs.*;
import static io.restassured.RestAssured.given;

public class TaskCreationApiSteps {
    @Step("API: создаем новую задачу с параметрами 'content'='{content}', 'description'='{description}', 'due_string'='{due}'")
    public static TaskResponse createNewTask(String content, String description, String due) {
        TaskRequest request = new TaskRequest();
        request.setContent(content);
        request.setDescription(description);
        request.setDueString(due);

        TaskResponse response = createNewTask(request);

        return response;
    }

    @Step("API: создаем новую задачу")
    public static TaskResponse createNewTask(TaskRequest request) {
        TaskResponse response = given()
                .spec(taskRequestSpec)
                .body(request)
                .when()
                .post()
                .then()
                .spec(taskResponseSpec)
                .extract()
                .as(TaskResponse.class);

        return response;
    }

    @Step("API: пытаемся создать новую задачу без задания обязательного параметра 'content'")
    public static String createNewTaskWithError(String description, String due) {
        TaskRequest request = new TaskRequest();
        request.setDescription(description);
        request.setDueString(due);

        String response = given()
                .spec(taskRequestSpec)
                .body(request)
                .when()
                .post()
                .then()
                .spec(errorResponseSpec)
                .extract().body().asString();

        return response;
    }
}
