package se.hrmsoftware.examples.todo;

import com.vaadin.ui.Table;

import java.util.concurrent.atomic.AtomicInteger;

public class ItemsTable extends Table {
	private AtomicInteger maxTableId = new AtomicInteger(0);
	public ItemsTable(String tasksTitle) {
		addContainerProperty(tasksTitle, String.class, null);
		setSizeFull();
	}

	public void addTaskItem(Object value) {
		addItem(new Object[] { value }, maxTableId.incrementAndGet());
	}
}
