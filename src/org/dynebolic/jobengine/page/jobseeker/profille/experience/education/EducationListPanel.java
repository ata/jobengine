package org.dynebolic.jobengine.page.jobseeker.profille.experience.education;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.JobSeekerEducation;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerEducationService;

@SuppressWarnings("serial")
public abstract class EducationListPanel extends BasePanel{
	private JobSeekerEducationService jobSeekerEducationService =
		new JobSeekerEducationService();
	private List<JobSeekerEducation> educations;
	public EducationListPanel(String id){
		this(id,false);
	}
	
	public EducationListPanel(String id, Boolean reload) {
		super(id);
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
				item.setModel(new CompoundPropertyModel(education));
				item.add(new Label("level.name"));
				item.add(new Label("faculty"));
				item.add(new Label("institute"));
				item.add(new Label("start"));
				item.add(new Label("done"));
				item.add(new Label("ipk"));
				item.add(new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						jobSeekerEducationService.remove(education);
						onDeleteItem(target);
					}
					
				});
			}
		};
		add(eachEducation);
	}
	abstract protected void onDeleteItem(AjaxRequestTarget target);

}
