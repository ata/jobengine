package org.dynebolic.jobengine.page.jobseeker.profille.carier.carier;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.NumberValidator;
import org.dynebolic.jobengine.entity.JobSeekerCarier;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerCarierService;

@SuppressWarnings("serial")
public abstract class CarierFormPanel extends BasePanel{
	private JobSeekerCarierService carierService = 
		new JobSeekerCarierService();
	private JobSeekerCarier carier;
	public CarierFormPanel(String id) {
		super(id);
		
		carier = new JobSeekerCarier();
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);
        
		Form form = new Form("form",new CompoundPropertyModel(carier));
		add(form);
		form.setOutputMarkupId(true);
		
		FormComponent fc;
		
		fc = new RequiredTextField("institute");
		form.add(fc);
		
		fc = new RequiredTextField("position");
		form.add(fc);
		
		fc = new RequiredTextField("start",Integer.class);
		fc.add(NumberValidator.range(1950, 2050));
		form.add(fc);
		
		fc = new RequiredTextField("done",Integer.class);
		fc.add(NumberValidator.range(1950, 2050));
		form.add(fc);
	
		AjaxButton submit = new AjaxButton("submit"){
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				carier.setJobSeeker(getJESession().getUser().getJobSeeker());
				carierService.save(carier);
				onSubmitAjax(target);
			}

			protected void onError(AjaxRequestTarget target, Form form) {
				target.addComponent(feedback);
			}
			
		};
		form.add(submit);
	}
	abstract protected void onSubmitAjax(AjaxRequestTarget target);
}
