package org.dynebolic.jobengine.page.jobseeker.profille.language;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.JobSeekerLanguage;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerLanguageService;
import org.dynebolic.jobengine.service.LanguageService;

@SuppressWarnings("serial")
public abstract class LanguageFormPanel extends BasePanel{
	private JobSeekerLanguageService jobSeekerLanguageService = 
		new JobSeekerLanguageService();
	private LanguageService languageService = new LanguageService();
	private JobSeekerLanguage jobSeekerLanguage;
	public LanguageFormPanel(String id, JobSeekerLanguage jsl) {
		super(id);
		if(jsl == null){
			jobSeekerLanguage = new JobSeekerLanguage();
		} else {
			jobSeekerLanguage = jsl;
		}

		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);
        
		Form form = new Form("form",new CompoundPropertyModel(jobSeekerLanguage));
		add(form);
		form.setOutputMarkupId(true);
		
		FormComponent fc;
		
		
		
		List<String> levels = new ArrayList<String>();
		levels.add("Pasive");
		levels.add("Active");
		fc = new DropDownChoice("level",levels);
		fc.setRequired(true);
		form.add(fc);
		
		fc = new DropDownChoice("language",languageService.find(),
				new ChoiceRenderer("name","id"));
		fc.setRequired(true);
		form.add(fc);
		
		
		AjaxButton submit = new AjaxButton("submit"){
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				jobSeekerLanguage.setJobSeeker(getJESession().getUser().getJobSeeker());
				jobSeekerLanguageService.save(jobSeekerLanguage);
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
	protected abstract void onCancel(AjaxRequestTarget target);
	abstract protected void onSubmitAjax(AjaxRequestTarget target);
}
