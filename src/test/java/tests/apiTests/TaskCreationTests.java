package tests.apiTests;

import allure.Layer;
import api.models.requests.TaskRequest;
import api.models.responses.TaskResponse;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.steps.TaskCreationApiSteps.newTaskCreation;
import static api.steps.TaskCreationApiSteps.newTaskCreationWithError;
import static api.steps.TaskRemovalApiSteps.cleanupAllTasks;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("m.remneva")
@Layer("rest")
@Tag("api")
@Feature("Задачи")
@Story("Создание задач")
public class TaskCreationTests extends ApiTestBase {
    @Test
    @DisplayName("Создание новой задачи")
    public void newTaskCreationTest() {
        String content = "Content";
        String description = "Description";
        String due = "Сегодня";

        TaskResponse response = newTaskCreation(content, description, due);

        step("Проверяем контент созданной задачи", () -> {
            assertThat(response.getContent()).isEqualTo(content);
            assertThat(response.getDescription()).isEqualTo(description);
            assertThat(response.getIsCompleted()).isFalse();
            assertThat(response.getDue().getDueString()).isEqualTo(due);
        });
    }

    @Test
    @DisplayName("Создания новой задачи с единственным заполненным полем 'content'")
    public void newSimpleTaskCreationTest() {
        String content = "Content";
        TaskRequest request = new TaskRequest();
        request.setContent(content);

        TaskResponse response = newTaskCreation(request);

        step("Проверяем контент созданной задачи", () -> {
            assertThat(response.getContent()).isEqualTo(content);
            assertThat(response.getIsCompleted()).isFalse();
        });
    }

    @Test
    @DisplayName("Задачу невозможно создать без заполнения поля 'content'")
    public void failedNewTaskCreationWithoutContentTest() {
        String description = "Description";
        String due = "Сегодня";

        String response = newTaskCreationWithError(description, due);

        step("Проверяем корректность сообщения об ошибке", () ->
                assertThat(response).isEqualTo("Required argument is missing")
        );
    }

    @AfterEach
    void cleanup() {
        cleanupAllTasks();
    }

}
