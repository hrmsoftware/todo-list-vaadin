package se.hrmsoftware.examples.todo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.concurrent.atomic.AtomicInteger;

@Theme("mytheme")
@SuppressWarnings("serial")
public class TodoListVaadinUI extends UI
{

    private ItemsTable table;
    private NewItemComponent newItemComponent;

    @WebServlet(value = "/todo", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = TodoListVaadinUI.class, widgetset = "se.hrmsoftware.examples.todo.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        newItemComponent = createNewItemComponent();
        layout.addComponent(newItemComponent);
        table = createListComponent();
        layout.addComponent(table);
    }

    private ItemsTable createListComponent() {
        return new ItemsTable("Tasks");
    }

    private NewItemComponent createNewItemComponent() {
        final NewItemComponent itemComponent = new NewItemComponent();
        itemComponent.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                addItemFromField();
            }
        });
        itemComponent.addShortcutListener(new ShortcutListener("Add Task", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object o, Object o1) {
                addItemFromField();
            }
        });
        return itemComponent;
    }

    private void addItemFromField() {
        table.addTaskItem(newItemComponent.getValue());
        newItemComponent.clearValue();
    }
}
