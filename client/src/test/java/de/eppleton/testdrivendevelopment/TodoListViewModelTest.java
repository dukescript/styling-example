package de.eppleton.testdrivendevelopment;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class TodoListViewModelTest {

    @Test
    public void addButtonEnabled() {
        TodoListViewModel model = new TodoListViewModel();
        model.setInputText("Bu");
        assertFalse(model.isAddEnabled());
        model.setInputText("Buy");
        assertTrue(model.isAddEnabled());
        model.setInputText("Bu");
        assertFalse(model.isAddEnabled());
    }

    @Test
    public void addButtonAdd() {
        TodoListViewModel model = new TodoListViewModel();
        assertEquals(model.getTodos().size(), 0);
        model.setInputText("bu");
        model.addTodo();
        assertEquals(model.getTodos().size(), 0);
        model.setInputText("buy milk");
        model.addTodo();
        assertEquals(model.getTodos().size(), 1);
        assertEquals("", model.getInputText());
    }

    @Test
    public void deleteButtonEnabled() {
        TodoListViewModel model = new TodoListViewModel();
        assertFalse(model.isDeleteEnabled());
        model.getTodos().add("buy Milk");
        model.getSelected().add("buy milk");
        assertTrue(model.isDeleteEnabled());
    }

    @Test
    public void deleteButtonDelete() {
        TodoListViewModel model = new TodoListViewModel();
        model.getTodos().add("buy Milk");
        assertEquals(model.getTodos().size(), 1);
        model.getSelected().add("buy Milk");
        model.deleteTodo();
        assertEquals(model.getTodos().size(), 0);
        assertEquals(model.getSelected().size(), 0);
    }
}
