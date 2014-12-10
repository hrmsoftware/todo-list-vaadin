package se.hrmsoftware.examples.todo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import java.util.concurrent.atomic.AtomicInteger;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    private Table table;
    private AtomicInteger maxTableId = new AtomicInteger(0);
    private TextField field;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "se.hrmsoftware.examples.todo.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        layout.addComponent(createNewItemComponent());
        layout.addComponent(createListComponent());
    }

    private Component createListComponent() {
        table = new Table();
        table.addContainerProperty("Tasks", String.class, null);
        table.setSizeFull();
        return table;
    }

    private Component createNewItemComponent() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();

        field = createTextField();
        layout.addComponent(field);

        layout.addComponent(createButton());

        return layout;
    }

    private Button createButton() {
        Button button = new Button("Add Task");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                addItemFromField();
            }
        });
        return button;
    }

    private TextField createTextField() {
        TextField field = new TextField();

        field.focus();
        field.setInputPrompt("Task");
        field.setImmediate(true);
        field.setSizeFull();

        field.addShortcutListener(new ShortcutListener("Add Task", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object o, Object o1) {
                addItemFromField();
            }
        });
        return field;
    }

    private void addItemFromField() {
        table.addItem(new Object[] { field.getValue() }, maxTableId.incrementAndGet());
        field.setValue("");
    }

}
