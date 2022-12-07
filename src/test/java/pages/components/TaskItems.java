package pages.components;

import com.codeborne.selenide.ElementsCollection;
import pages.elements.Elements;

import static com.codeborne.selenide.CollectionCondition.size;

public class TaskItems extends Elements {

    public TaskItems(String name, ElementsCollection selector) {
        super(name, selector);
    }

    public TaskItem getTaskItemByIndex(int index) {
        return new TaskItem("taskItem" + index, this.selector.get(index));
    }

    public void checkNoTasksPresent() {
        selector.shouldHave(size(0));
    }

    public void removeAllTasks() {
        while (this.selector.size() > 0) {
            int tasksCount = this.selector.size();
            TaskItem item = new TaskItem("taskItem", this.selector.get(tasksCount - 1));
            item.removeTaskItem();
            this.selector.shouldHave(size(tasksCount - 1));
        }
    }
}
