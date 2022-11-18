package api.steps;

import api.models.responses.TaskResponse;
import io.qameta.allure.Step;

import static api.specs.TaskSpecs.*;
import static io.restassured.RestAssured.given;

public class TaskApiSteps {
    @Step("API: получаем задачу с id='{id}'")
    public static TaskResponse getTaskById(Long id) {
        TaskResponse task = given()
                .spec(taskRequestSpec)
                .pathParam("id", id)
                .when()
                .get("{id}")
                .then()
                .spec(taskResponseSpec)
                .extract()
                .as(TaskResponse.class);
        return task;
    }

    @Step("API: проверяем наличие задачи с id='{id}'")
    public static boolean isTaskExistsById(Long id) {
        int code = given()
                .spec(taskRequestSpec)
                .pathParam("id", id)
                .when()
                .get("{id}")
                .then()
                .spec(simpleResponseSpec)
                .extract()
                .statusCode();
        return code != 404;
    }

    @Step("API: Завершаем задачу c id='{id}'")
    public static void completeTask(Long id) {
        given()
                .spec(taskRequestSpec)
                .pathParam("id", id)
                .when()
                .post("{id}/close")
                .then()
                .spec(taskDeletionResponseSpec);
    }

    @Step("API: Переоткрываем задачу c id='{id}'")
    public static void reopenTask(Long id) {
        given()
                .spec(taskRequestSpec)
                .pathParam("id", id)
                .when()
                .post("{id}/reopen")
                .then()
                .spec(taskDeletionResponseSpec);
    }

}
