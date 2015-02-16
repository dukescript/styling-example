package de.eppleton.testdrivendevelopment;

import java.util.List;
import net.java.html.json.ComputedProperty;
import net.java.html.json.Function;
import net.java.html.json.Model;
import net.java.html.json.ModelOperation;
import net.java.html.json.Property;

/**
 * Model annotation generates class Data with one message property, boolean
 * property and read only words property
 */
@Model(className = "TodoListViewModel", targetId = "", properties = {
    @Property(name = "inputText", type = String.class),
    @Property(name = "todos", type = String.class, array = true),
    @Property(name = "selected", type = String.class, array = true)
})
final class TodoListViewModelDefinition {

    @ComputedProperty
    static boolean addEnabled(String inputText) {
        return isValidInput(inputText);
    }

    @Function
    @ModelOperation
    public static void addTodo(TodoListViewModel model) {
        if (isValidInput(model.getInputText())) {
            model.getTodos().add(model.getInputText());
            model.setInputText("");
        }
    }

    private static boolean isValidInput(String inputText) {
        return (inputText != null && inputText.length() > 2);
    }

    @ComputedProperty
    static boolean deleteEnabled(List<String> selected) {
        return (selected != null && selected.size() > 0);
    }
    
    @Function
    @ModelOperation
    public static void deleteTodo(TodoListViewModel model) {
        List<String> selected = model.getSelected();
        for (String selected1 : selected) {
            model.getTodos().remove(selected1);
        }
        model.getSelected().clear();
    }


}
