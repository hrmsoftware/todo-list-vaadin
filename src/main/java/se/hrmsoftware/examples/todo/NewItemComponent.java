package se.hrmsoftware.examples.todo;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class NewItemComponent extends HorizontalLayout {
	private TextField field;
	private Button button;

	public NewItemComponent() {
		setSizeFull();
		field = createTextField();
		addComponent(field);
		button = createButton();
		addComponent(button);
	}
	private TextField createTextField() {
		TextField field = new TextField();

		field.focus();
		field.setInputPrompt("Task");
		field.setImmediate(true);
		field.setSizeFull();

		return field;
	}
	private Button createButton() {
		return new Button("Add Task");
	}

	public void addClickListener(Button.ClickListener clickListener) {
		button.addClickListener(clickListener);
	}

	public Object getValue() {
		return field.getValue();
	}

	public void clearValue() {
		field.setValue("");
	}
}
