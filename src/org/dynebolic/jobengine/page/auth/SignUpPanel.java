package org.dynebolic.jobengine.page.auth;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.dynebolic.jobengine.entity.Employer;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.Role;
import org.dynebolic.jobengine.entity.User;
import org.dynebolic.jobengine.service.EmployerService;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.jobengine.service.RoleService;
import org.dynebolic.jobengine.service.UserService;

@SuppressWarnings("serial")
public class SignUpPanel extends Panel {
	private User user;
	private String validatePassword;
	private RoleService roleService = new RoleService();
	private UserService userService = new UserService();
	private JobSeekerService jobSeekerService = new JobSeekerService();
	private EmployerService employerService = new EmployerService();
	public SignUpPanel(String id) {
		super(id);
		user = new User();
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);
        feedback.setMarkupId("signUpFeedback");
        
		Form form = new Form("signUpForm", new CompoundPropertyModel(user));
		add(form);
		form.setOutputMarkupId(true);
		
		FormComponent fc;
		
		ChoiceRenderer choiceRenderer = new ChoiceRenderer("name","id");
		List<Role> roles = roleService.find();
		fc = new DropDownChoice("role",roles,choiceRenderer);
		fc.setRequired(true);
        form.add(fc);
		
		fc = new TextField("name");
		fc.setRequired(true);
		form.add(fc);
		
		fc = new TextField("username");
		fc.setRequired(true);
		fc.add(new IValidator(){

			@Override
			public void validate(IValidatable validatable) {
				String username = (String) validatable.getValue();
                if (!userService.validUsername(username)) {
                        validatable.error(new ValidationError().addMessageKey(
                                        "error.usernametaken").setVariable("username",
                                        validatable.getValue()));
                }
				
			}
			
		});
		form.add(fc);
		
		fc = new TextField("email");
		fc.setRequired(true);
		fc.add(EmailAddressValidator.getInstance());
		fc.add(new IValidator(){

			@Override
			public void validate(IValidatable validatable) {
				String email = (String) validatable.getValue();
                if (!userService.validEmail(email)) {
                        validatable.error(new ValidationError().addMessageKey(
                                        "error.emailtaken").setVariable("email",
                                        validatable.getValue()));
                }
				
			}
			
		});
		form.add(fc);
		
		PasswordTextField password = new PasswordTextField("password");
		password.setRequired(true);
		password.add(StringValidator.minimumLength(6));
		
		PasswordTextField validatePassword = 
			new PasswordTextField("validatePassword",new PropertyModel(this,"validatePassword"));
		
		validatePassword.setRequired(true);
		
		
		form.add(new EqualPasswordInputValidator(password,validatePassword));
		form.add(validatePassword).add(password);
		
		AjaxButton submit = new AjaxButton("submit", form){

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				user.setComplete(false);
				if(user.getRole().getName().equalsIgnoreCase("JobSeeker")){
					JobSeeker jobSeeker = new JobSeeker();
					jobSeeker.setName(user.getName());
					jobSeeker.setUser(user);
					jobSeekerService.save(jobSeeker);
				} else {
					Employer employer = new Employer();
					employer.setUser(user);
					employer.setName(user.getName());
					employerService.save(employer);
				}
				target.addComponent(feedback);
				target.appendJavascript("Modalbox.show($('successRegister'))");
				
			}
			
			protected void onError(AjaxRequestTarget target, Form form)
            {
                target.addComponent(feedback);
                target.appendJavascript("Modalbox.resizeToContent();" +
                		"new Effect.Shake('MB_window')");
            }
			
		};
		form.add(submit);
		
	}

	/**
	 * @param validatePassword the validatePassword to set
	 */
	public void setValidatePassword(String validatePassword) {
		this.validatePassword = validatePassword;
	}

	/**
	 * @return the validatePassword
	 */
	public String getValidatePassword() {
		return validatePassword;
	}

}
