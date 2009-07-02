package org.dynebolic.jobengine.page.message;

import java.util.Calendar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.Message;
import org.dynebolic.jobengine.entity.User;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.MessageService;
import org.dynebolic.jobengine.service.UserService;

@SuppressWarnings("serial")
public abstract class MessageViewPanel extends BasePanel {

	public MessageViewPanel(String id, Message message) {
		super(id);
		setModel(new CompoundPropertyModel(message));
		add(new Label("messageFrom.name"));
		add(new Label("subject"));
		add(new Label("body", message.getBody()
				.replaceAll("\n", "<br/>")).setEscapeModelStrings(false));
		add(new AjaxLink("closeLink"){
			public void onClick(AjaxRequestTarget target) {
				onClose(target);
				
			}
			
		});
		
	}
	protected abstract void onClose(AjaxRequestTarget target);
}
