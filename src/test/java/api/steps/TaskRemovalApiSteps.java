package api.steps;

import api.models.responses.TaskResponse;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;

import java.util.Arrays;
import java.util.List;

import static api.specs.TaskSpecs.*;
import static io.restassured.RestAssured.given;

public class TaskRemovalApiSteps {
    @Step("API: удаляем все задачи из аккаунта")
    public static void cleanupAllTasks() {
        JsonPath response = given()
                .spec(taskRequestSpec)
                .when()
                .get()
                .then()
                .spec(taskResponseSpec)
                .extract()
                .body().
                jsonPath();

        List<TaskResponse> tasks = Arrays.asList(response.getObject("$", TaskResponse[].class));

        for (TaskResponse task : tasks) {
            deleteTask(task);
        }
    }

    @Step("API: Удаляем задачу c id='{id}'")
    public static void deleteTask(Long id) {
        given()
                .spec(taskRequestSpec)
                .pathParam("id", id)
                .when()
                .delete("{id}")
                .then()
                .spec(taskDeletionResponseSpec);
    }

    @Step("API: Удаляем задачу")
    public static void deleteTask(TaskResponse task) {
        Long id = task.getId();
        deleteTask(id);
    }
}
