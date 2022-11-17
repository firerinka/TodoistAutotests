package api.steps;

import api.models.requests.TaskRequest;
import api.models.responses.TaskResponse;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;

import java.util.Arrays;
import java.util.List;

import static api.specs.TaskSpecs.*;
import static io.restassured.RestAssured.given;

public class TaskAPISteps {
    @Step("API: создаем новую задачу с параметрами 'content'='{content}', 'description'='{description}', 'due_string'='{due}'")
    public static TaskResponse newTaskCreation(String content, String description, String due) {
        TaskRequest request = new TaskRequest();
        request.setContent(content);
        request.setDescription(description);
        request.setDueString(due);

        TaskResponse response = newTaskCreation(request);

        return response;
    }

    @Step("API: создаем новую задачу")
    public static TaskResponse newTaskCreation(TaskRequest request) {
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
    public static String newTaskCreationWithError(String description, String due) {
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

    @Step("API: удаляем все задачи из аккаунта")
    public static void cleanUpAllTasks() {
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
                .basePath("tasks/" + id.toString())
                .when()
                .delete()
                .then()
                .spec(taskDeletionResponseSpec);
    }

    @Step("API: Удаляем задачу")
    public static void deleteTask(TaskResponse task) {
        Long id = task.getId();
        deleteTask(id);
    }
}
