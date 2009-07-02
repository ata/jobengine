package org.dynebolic.jobengine.page.employer.job;

import java.text.DateFormat;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.jobengine.service.JobService;
import org.dynebolic.library.PictureViewPanel;

@SuppressWarnings("serial")
public abstract class JobPreviewPanel extends BasePanel {
	private Job job;
	private JobService jobService = new JobService();
	private JobSeekerService jobSeekerService = new JobSeekerService();
	private DateFormat dateFormat =  DateFormat.getDateInstance(DateFormat.FULL);
	WebMarkupContainer jsMiscContainer;
	public JobPreviewPanel(String id,Long jobId) {
		super(id);
		job = jobService.find(jobId);
		setModel(new CompoundPropertyModel(job));
		employer();
		job();
		add(new AjaxLink("closeLink"){
			public void onClick(AjaxRequestTarget target) {
				onClose(target);
			}
			
		});
		jsMiscContainer = new WebMarkupContainer("jsMiscContainer");
		add(jsMiscContainer);
		jsMiscContainer.add(new AjaxLink("submitLink"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				if(job.getSubmiteds().add(getJESession().getUser().getJobSeeker())){
					jobService.save(job);
					target.appendJavascript("$('successSubmit').show().fade({duration:2});");
				} else {
					target.appendJavascript("$('warningSubmit').show().fade({duration:2});");
				}
				
			}
			
		});
		jsMiscContainer.add(new AjaxLink("bookmarksLink"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				if(getJESession().getUser().getJobSeeker().getBookmarks().add(job)){
					jobSeekerService.save(getJESession().getUser().getJobSeeker());
					target.appendJavascript("$('successBookmarks').show().fade({duration:2});");
				} else {
					target.appendJavascript("$('warningBookmarks').show().fade({duration:2});");
				}
				
			}
			
		});
		if(getJESession().getRoles().toString().equalsIgnoreCase("JobSeeker")){
			jsMiscContainer.setVisible(true);
		}
		
		
	}
	private void job() {
		add(new Label("title"));
		add(new Label("description"));
		add(new Label("category.name"));
		add(new Label("position"));
		add(new Label("experience"));
		add(new Label("education.name"));
		add(new Label("salary"));
		add(new Label("faculty"));
		add(new Label("country.name"));
		add(new Label("location.name"));
		add(new Label("skillRequirements"));
		add(new Label("additionalSkillRequirements"));
		add(new Label("additionalRequirements"));
		add(new Label("expired",dateFormat.format(job.getExpired())));
		
	}
	protected abstract void onClose(AjaxRequestTarget target);
	private void employer() {
		add(new PictureViewPanel("photo",job.getEmployer().getPhoto(), 100, 100));
		add(new Label("employer.name"));
		add(new Label("employer.category.name"));
		add(new Label("employer.country.name"));
		add(new Label("employer.location.name"));
		add(new Label("employer.description", job.getEmployer().getDescription()
				.replaceAll("\n", "<br/>")).setEscapeModelStrings(false));
		
	}

}
