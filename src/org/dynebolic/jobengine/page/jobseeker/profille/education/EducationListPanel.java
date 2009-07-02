package org.dynebolic.jobengine.page.jobseeker.profille.education;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.JobSeekerEducation;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerEducationService;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.library.JavascriptEventConfirmation;

@SuppressWarnings("serial")
public abstract class EducationListPanel extends BasePanel{
	private JobSeekerEducationService jobSeekerEducationService =
		new JobSeekerEducationService();
	private JobSeekerService jobSeekerService = new JobSeekerService();
	private List<JobSeekerEducation> educations;
	private JobSeeker jobSeeker;
	public EducationListPanel(String id){
		this(id,false);
	}
	
	public EducationListPanel(String id, Boolean reload) {
		super(id);
		jobSeeker = getJESession().getUser().getJobSeeker();
		if(reload) {
			educations = jobSeekerEducationService.find(getJESession()
					.getUser().getJobSeeker());
		} else {
			educations = getJESession().getUser().getJobSeeker().getEducations();
		}
		final ListView eachEducation = new ListView("eachEducation", educations){
			protected void populateItem(final ListItem item) {
				final JobSeekerEducation education = 
					(JobSeekerEducation) item.getModelObject();
				item.setOutputMarkupId(true);
				item.setModel(new CompoundPropertyModel(education));
				
				item.add(new Label("level.name"));
				item.add(new Label("faculty"));
				item.add(new Label("institute"));
				item.add(new Label("start"));
				item.add(new Label("done"));
				item.add(new Label("ipk"));
				
				
				Component lastLabel = null;
				if(education.isLast()){
					lastLabel = new Label("lastLabel","is last");
				} else {
					lastLabel = new Label("lastLabel","-");
				}
				item.add(lastLabel);
				AjaxLink editLink = new AjaxLink("editLink"){
					public void onClick(AjaxRequestTarget target) {
						onEdit(target, education);
					}
					
				};
				
				item.add(editLink);
				
				AjaxLink deleteLink = new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						if(education.isLast()){
							jobSeeker.setLastEducation(null);
							jobSeekerEducationService.resetLast();
							jobSeekerService.save(jobSeeker);
						}
						jobSeekerEducationService.remove(education);
						target.appendJavascript("$('"+item.getMarkupId()+"').addClassName('delete').fade()");
						//onDeleteItem(target);
					}
					
				};
				deleteLink.add(new JavascriptEventConfirmation("onclick", "are you sure?"));
				if(education.isLast()){
					//deleteLink.setVisible(false);
				}
				item.add(deleteLink);
			}
		};
		add(eachEducation);
	}
	protected abstract void onDeleteItem(AjaxRequestTarget target);
	protected abstract void onEdit(AjaxRequestTarget target,JobSeekerEducation jse);

}
