package org.dynebolic.jobengine.page.jobseeker.profille.carier.education;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.NumberValidator;
import org.dynebolic.jobengine.entity.EducationLevel;
import org.dynebolic.jobengine.entity.JobSeekerEducation;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.EducationLevelService;
import org.dynebolic.jobengine.service.JobSeekerEducationService;

@SuppressWarnings("serial")
public abstract class EducationFormPanel extends BasePanel{
	private EducationLevelService educationLevelService = 
		new EducationLevelService();
	private JobSeekerEducationService jobSeekerEducationService =
		new JobSeekerEducationService();
	private JobSeekerEducation jobSeekerEducation;
	public EducationFormPanel(String id) {
		super(id);
		
		jobSeekerEducation = new JobSeekerEducation();
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);
        
		Form form = new Form("educationForm",new CompoundPropertyModel(jobSeekerEducation));
		add(form);
		form.setOutputMarkupId(true);
		form.setMarkupId("educationForm");
		
		FormComponent fc;
		
		ChoiceRenderer choiceRenderer = new ChoiceRenderer("name","id");
		List<EducationLevel> levels = educationLevelService.find();
		fc = new DropDownChoice("level",levels,choiceRenderer);
		fc.setRequired(true);
		form.add(fc);
		
		fc = new RequiredTextField("faculty");
		form.add(fc);
		
		fc = new RequiredTextField("institute");
		form.add(fc);
		
		fc = new RequiredTextField("start",Integer.class);
		fc.add(NumberValidator.range(1950, 2050));
		form.add(fc);
		
		fc = new RequiredTextField("done",Integer.class);
		fc.add(NumberValidator.range(1950, 2050));
		form.add(fc);
		
		fc = new RequiredTextField("ipk",Double.class);
		fc.add(NumberValidator.range(0, 4));
		form.add(fc);
		
		AjaxButton submit = new AjaxButton("submit"){
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				jobSeekerEducation.setJobSeeker(getJESession().getUser().getJobSeeker());
				jobSeekerEducationService.save(jobSeekerEducation);
				onSubmitAjax(target);
				/*
				AjaxLazyLoadPanel ajaxLoad = new AjaxLazyLoadPanel("educationListPanel") {
					public Component getLazyLoadComponent(String id) {
						return new LanguageListPanel(id);
					}
				};
				ajaxLoad.setOutputMarkupId(true);
				panel.addOrReplace(ajaxLoad);
				target.addComponent(ajaxLoad);
				*/
				//panel.loadEducationListPanel(target);
				//panel.getForm().setVisible(false);
				
			}

			protected void onError(AjaxRequestTarget target, Form form) {
				target.addComponent(feedback);
			}
			
		};
		form.add(submit);
	}
	abstract protected void onSubmitAjax(AjaxRequestTarget target);
}
