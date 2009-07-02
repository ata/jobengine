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
public abstract class MessagePanel extends BasePanel {
	private UserService userService = new UserService();
	private MessageService messageService = new MessageService();
	private User userFrom;
	private User userTo;
	private Message message;
	public MessagePanel(String id, Long userId) {
		super(id);
		userFrom = getJESession().getUser();
		System.out.println(userFrom);
		userTo = userService.find(userId);
		message = new Message();
		
		add(new Label("toLabel",userTo.getName()));
		
		final FeedbackPanel feedback= new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);
        
        Form form = new Form("form",new CompoundPropertyModel(message));
		form.setOutputMarkupId(true);
		add(form);
		
		FormComponent fc;
		fc = new RequiredTextField("subject");
		form.add(fc);
		
		fc = new TextArea("body");
		fc.setRequired(true);
		form.add(fc);

		AjaxButton submit = new AjaxButton("submit"){
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				message.setMessageFrom(userFrom);
				message.setMessageTo(userTo);
				message.setReaded(false);
				message.setMessageDate(Calendar.getInstance().getTime());
				messageService.save(message);
				onAjaxSubmit(target);
				target.addComponent(feedback);
			}
			protected void onError(AjaxRequestTarget target, Form form) {
				target.addComponent(feedback);
			}
			
		};
		form.add(submit);
		form.add(new AjaxLink("cancel"){
			public void onClick(AjaxRequestTarget target) {
				onCancel(target);
			}
		});
	}
	protected abstract void onAjaxSubmit(AjaxRequestTarget target);
	protected abstract void onCancel(AjaxRequestTarget target);
}
