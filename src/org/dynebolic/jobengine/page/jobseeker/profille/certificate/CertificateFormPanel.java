package org.dynebolic.jobengine.page.jobseeker.profille.certificate;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.NumberValidator;
import org.dynebolic.jobengine.entity.JobSeekerCertificate;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerCertificateService;

@SuppressWarnings("serial")
public abstract class CertificateFormPanel extends BasePanel{
	private JobSeekerCertificateService certificateService = 
		new JobSeekerCertificateService();
	private JobSeekerCertificate jobSeekerCertificate;
	public CertificateFormPanel(String id, JobSeekerCertificate jsc) {
		super(id);
		if(jsc == null) {
			jobSeekerCertificate= new JobSeekerCertificate();
		} else {
			jobSeekerCertificate = jsc;
		}
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);
        
		Form form = new Form("form",new CompoundPropertyModel(jobSeekerCertificate));
		add(form);
		form.setOutputMarkupId(true);
		
		FormComponent fc;
		
		fc = new RequiredTextField("name");
		form.add(fc);
		
		fc = new RequiredTextField("year",Integer.class);
		fc.add(NumberValidator.range(1950, 2050));
		form.add(fc);
	
		AjaxButton submit = new AjaxButton("submit"){
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				jobSeekerCertificate.setJobSeeker(getJESession().getUser().getJobSeeker());
				certificateService.save(jobSeekerCertificate);
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
