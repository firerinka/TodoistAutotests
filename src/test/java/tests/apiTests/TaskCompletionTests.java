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
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("m.remneva")
@Layer("rest")
@Tag("api")
@Feature("Задачи")
@Story("Завершение задач")
public class TaskCompletionTests extends ApiTestBase {
    @Test
    @DisplayName("Завершение новой задачи")
    public void taskCompletionTest() {
        String content = "Content";
        String description = "Description";
        String due = "Сегодня";

        TaskResponse response = TaskCreationApiSteps.createNewTask(content, description, due);
        assertThat(isTaskExistsById(response.getId())).isTrue();
        assertThat(response.getIsCompleted()).isFalse();

        completeTask(response.getId());

        TaskResponse response2 = getTaskById(response.getId());
        step("Проверяем, что задача существует и успешно завершена", () -> {
            assertThat(isTaskExistsById(response2.getId())).isTrue();
            assertThat(response2.getIsCompleted()).isTrue();
        });
    }

    @Test
    @DisplayName("Переоткрытие закрытой задачи")
    public void taskReopenTest() {
        String content = "Content";
        String description = "Description";
        String due = "Сегодня";

        TaskResponse response = TaskCreationApiSteps.createNewTask(content, description, due);
        completeTask(response.getId());
        reopenTask(response.getId());

        TaskResponse response2 = getTaskById(response.getId());
        step("Проверяем, что задача успешно переоткрыта", () -> {
            assertThat(response2.getIsCompleted()).isFalse();
        });
    }

    @AfterEach
    void cleanup() {
        cleanupAllTasks();
    }
}
