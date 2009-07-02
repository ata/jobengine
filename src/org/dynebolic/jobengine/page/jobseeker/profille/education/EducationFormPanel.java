package org.dynebolic.jobengine.page.jobseeker.profille.education;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.NumberValidator;
import org.dynebolic.jobengine.entity.EducationLevel;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.JobSeekerEducation;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.EducationLevelService;
import org.dynebolic.jobengine.service.JobSeekerEducationService;
import org.dynebolic.jobengine.service.JobSeekerService;

@SuppressWarnings("serial")
public abstract class EducationFormPanel extends BasePanel{
	private EducationLevelService educationLevelService = 
		new EducationLevelService();
	private JobSeekerEducationService jobSeekerEducationService =
		new JobSeekerEducationService();
	private JobSeekerService jobSeekerService = new JobSeekerService();
	private JobSeekerEducation jobSeekerEducation;
	
	public EducationFormPanel(String id,JobSeekerEducation jse) {
		super(id);
		if(jse == null){
			jobSeekerEducation = new JobSeekerEducation();
		}else{
			jobSeekerEducation = jse;
		}
		
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
		
		fc = new TextField("faculty");
		form.add(fc);
		
		fc = new RequiredTextField("institute");
		form.add(fc);
		
		fc = new RequiredTextField("start",Integer.class);
		fc.add(NumberValidator.range(1950, 2050));
		form.add(fc);
		
		fc = new RequiredTextField("done",Integer.class);
		fc.add(NumberValidator.range(1950, 2050));
		form.add(fc);
		
		fc = new TextField("ipk",Double.class);
		fc.add(NumberValidator.range(0, 4));
		form.add(fc);
		
		fc = new CheckBox("last");
		form.add(fc);
		
		AjaxButton submit = new AjaxButton("submit"){
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				jobSeekerEducation.setJobSeeker(getJESession().getUser().getJobSeeker());
				if(jobSeekerEducation.isLast()) {
					jobSeekerEducationService.resetLast();
					JobSeeker jobSeeker = getJESession().getUser().getJobSeeker();
					jobSeeker.setLastEducation(jobSeekerEducation);
					jobSeekerService.save(jobSeeker);
				} else {
					jobSeekerEducationService.save(jobSeekerEducation);
				}
				onSubmitAjax(target);
				
			}

			protected void onError(AjaxRequestTarget target, Form form) {
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
	abstract protected void onSubmitAjax(AjaxRequestTarget target);
	abstract protected void onCancel(AjaxRequestTarget target);
}
