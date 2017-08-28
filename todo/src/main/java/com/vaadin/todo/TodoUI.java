package com.vaadin.todo;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
public class TodoUI extends UI {

	private static final long serialVersionUID = 1L;
	private VerticalLayout root;

	@Autowired
	private TodoLayout todoLayout;

	@Override
	protected void init(VaadinRequest request) {
		setupLayout();
		addHeader();
		addForm();
		addTodoList();
		addDeleteButton();
	}

	private void setupLayout() {
		root = new VerticalLayout();
		root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(root);
	}

	private void addHeader() {
		Label header = new Label("TODOs");
		header.addStyleName(ValoTheme.LABEL_H1);
		root.addComponent(header);
	}

	private void addForm() {
		HorizontalLayout formLayout = new HorizontalLayout();
		formLayout.setWidth("80%");

		TextField task = new TextField();
		Button add = new Button();
		add.addStyleName(ValoTheme.BUTTON_PRIMARY);
		add.setIcon(VaadinIcons.PLUS);
		
		//add.addClickListener((c) -> add(task.getValue()));
		add.addClickListener(click -> {
			todoLayout.add(new Todo(task.getValue()));
			task.clear();
			task.focus();
		});
		task.focus();
		add.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		formLayout.addComponentsAndExpand(task);
		formLayout.addComponents(add);
		root.addComponent(formLayout);
	}

	private void addTodoList() {
		todoLayout.setWidth("80%");
		root.addComponent(todoLayout);
	}

	private void addDeleteButton() {
		root.addComponent(new Button("Delete completed", click ->{
			todoLayout.deleteCompleted();
		}));
	}

	/*private void add(String text) {
		Todo todo = new Todo(text);
		todoLayout.addTodo(todo);
	}*/

}
