package org.dynebolic.jobengine.page.jobseeker.profille.experience.skill;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.JobSeekerSkill;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerSkillService;

@SuppressWarnings("serial")
public abstract class SkillListPanel extends BasePanel{
	private JobSeekerSkillService  skillService = new JobSeekerSkillService();
	private List<JobSeekerSkill> skills;
	
	public SkillListPanel(String id){
		this(id,false);
	}
	
	public SkillListPanel(String id, Boolean reload) {
		super(id);
		
		if(reload) {
			skills = skillService.find(getJESession().getUser().getJobSeeker());
		} else {
			skills = getJESession().getUser().getJobSeeker().getSkills();
		}
		
		
		final ListView eachView = new ListView("eachView", skills){
			protected void populateItem(final ListItem item) {
				final JobSeekerSkill skill = (JobSeekerSkill) item.getModelObject();
				item.setModel(new CompoundPropertyModel(skill));
				item.add(new Label("name"));
				item.add(new Label("detail"));
				item.add(new Label("experience"));
				item.add(new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						skillService.remove(skill);
						onDeleteItem(target);
					}
					
				});
			}
		};
		add(eachView);
	}
	abstract protected void onDeleteItem(AjaxRequestTarget target);

}
