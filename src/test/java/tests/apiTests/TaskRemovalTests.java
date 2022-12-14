package tests.apiTests;

import api.steps.TaskCreationApiSteps;
import helpers.allureAnnotations.Layer;
import api.models.responses.TaskResponse;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.steps.TaskApiSteps.*;
import static api.steps.TaskRemovalApiSteps.cleanupAllTasks;
import static api.steps.TaskRemovalApiSteps.deleteTask;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("m.remneva")
@Layer("rest")
@Tag("api")
@Feature("Задачи")
@Story("Удаление задач")
public class TaskRemovalTests extends ApiTestBase {
    @Test
    @DisplayName("Удаление существующей задачи")
    public void taskRemovalTest() {
        String content = "Content";
        String description = "Description";
        String due = "Сегодня";

        TaskResponse response = TaskCreationApiSteps.createNewTask(content, description, due);
        assertThat(isTaskExistsById(response.getId())).isTrue();

        deleteTask(response.getId());

        step("Проверяем, что задача успешно удалена", () -> {
            assertThat(isTaskExistsById(response.getId())).isFalse();
        });
    }

    @Test
    @DisplayName("Удаление завершенной задачи")
    public void completedTaskRemovalTest() {
        String content = "Content";
        String description = "Description";
        String due = "Сегодня";

        TaskResponse response = TaskCreationApiSteps.createNewTask(content, description, due);
        completeTask(response.getId());
        deleteTask(response.getId());

        step("Проверяем, что задача успешно удалена", () -> {
            assertThat(isTaskExistsById(response.getId())).isFalse();
        });
    }

    @AfterEach
    void cleanup() {
        cleanupAllTasks();
    }
}
