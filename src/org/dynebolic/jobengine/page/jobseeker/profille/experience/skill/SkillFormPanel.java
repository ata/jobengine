package org.dynebolic.jobengine.page.jobseeker.profille.experience.skill;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.NumberValidator;
import org.dynebolic.jobengine.entity.JobSeekerSkill;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerSkillService;

@SuppressWarnings("serial")
public abstract class SkillFormPanel extends BasePanel{
	private JobSeekerSkillService skillService = new JobSeekerSkillService();
	private JobSeekerSkill skill;
	public SkillFormPanel(String id) {
		super(id);
		
		skill = new JobSeekerSkill();
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);
        
		Form form = new Form("form",new CompoundPropertyModel(skill));
		add(form);
		form.setOutputMarkupId(true);
		
		FormComponent fc;
		
		fc = new RequiredTextField("name");
		form.add(fc);
		
		fc = new RequiredTextField("detail");
		form.add(fc);
		
		fc = new RequiredTextField("experience",Integer.class);
		fc.add(NumberValidator.range(0, 60));
		form.add(fc);
	
		AjaxButton submit = new AjaxButton("submit"){
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				skill.setJobSeeker(getJESession().getUser().getJobSeeker());
				skillService.save(skill);
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
