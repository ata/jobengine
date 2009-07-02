package org.dynebolic.jobengine.page.auth.account;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.UserService;

@SuppressWarnings("serial")
public abstract class ChangeEmailPanel extends BasePanel {
	private String email;
	private UserService service = new UserService();
	public ChangeEmailPanel(String id) {
		super(id);
		
		email = getJESession().getUser().getEmail();
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);
        feedback.setMarkupId("signUpFeedback");
		
		Form form = new Form("form", new CompoundPropertyModel(this));
		add(form);
		
		FormComponent fc = new TextField("email");
		fc.setRequired(true);
		fc.add(EmailAddressValidator.getInstance());
		fc.add(new IValidator(){

			@Override
			public void validate(IValidatable validatable) {
				String email = (String) validatable.getValue();
                if (!service.validEmail(email)) {
                        validatable.error(new ValidationError().addMessageKey(
                                        "error.emailtaken").setVariable("email",
                                        validatable.getValue()));
                }
				
			}
			
		});
		form.add(fc);
		
		AjaxButton submit = new AjaxButton("submit", form){

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				
				
				service.emailUpdate(getEmail(), 
						getJESession().getUser().getId());
				getJESession().setUser(service.find(getJESession().getUser().getId()));
				onAjaxSubmit(target);
			}
			
			protected void onError(AjaxRequestTarget target, Form form)
            {
				
                target.addComponent(feedback);
            }
			
		};
		form.add(submit);
		form.add(new AjaxLink("cancel"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				onCancel(target);
			}
			
		});
	}
	protected abstract void onCancel(AjaxRequestTarget target);
	protected abstract void onAjaxSubmit(AjaxRequestTarget target);
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}

}
