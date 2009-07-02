package org.dynebolic.jobengine.page.auth.account;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.PasswordService;
import org.dynebolic.jobengine.service.UserService;


@SuppressWarnings("serial")
public abstract class ChangePasswordPanel extends BasePanel {
	private String password;
	private String validatePassword;
	private UserService service = new UserService();
	public ChangePasswordPanel(String id) {
		super(id);
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);
        feedback.setMarkupId("signUpFeedback");
		
		Form form = new Form("form", new CompoundPropertyModel(this));
		add(form);
		
		
		PasswordTextField password = new PasswordTextField("password");
		password.setRequired(true);
		password.add(StringValidator.minimumLength(6));
		
		PasswordTextField validatePassword = 
			new PasswordTextField("validatePassword");
		
		validatePassword.setRequired(true);
		
		
		form.add(new EqualPasswordInputValidator(password,validatePassword));
		form.add(validatePassword).add(password);
		
		AjaxButton submit = new AjaxButton("submit", form){

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				
				
				service.passwordUpdate(
						PasswordService.encrypt(ChangePasswordPanel.this.password), 
						getJESession().getUser().getId());
				
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
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setValidatePassword(String validatePassword) {
		this.validatePassword = validatePassword;
	}
	public String getValidatePassword() {
		return validatePassword;
	}

}
