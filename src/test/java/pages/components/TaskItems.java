package pages.components;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import elements.Elements;

public class TaskItems extends Elements {

    public TaskItems(String name, ElementsCollection selector) {
        super(name, selector);
    }

    public TaskItem getTaskItemByIndex(int index) {
        return new TaskItem("taskItem" + index, this.selector.get(index));
    }

    public void checkNoTasksPresent() {
        selector.shouldHave(CollectionCondition.size(0));
    }

    public void removeAllTasks() {
        while (this.selector.get(0).exists()) {
            TaskItem item = new TaskItem("taskItem", this.selector.get(0));
            item.removeTaskItem();
        }
    }
}
