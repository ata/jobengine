package org.dynebolic.jobengine.page.jobseeker.profille.skill;

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
import org.dynebolic.library.JavascriptEventConfirmation;

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
				item.setOutputMarkupId(true);
				item.add(new Label("name"));
				item.add(new Label("detail"));
				item.add(new Label("experience"));
				
				AjaxLink editLink = new AjaxLink("editLink"){
					public void onClick(AjaxRequestTarget target) {
						onEdit(target, skill);
					}
					
				};
				
				item.add(editLink);
				
				AjaxLink deleteLink = new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						skillService.remove(skill);
						target.appendJavascript("$('"+item.getMarkupId()+"').addClassName('delete').fade()");
						//onDeleteItem(target);
					}
					
				};
				item.add(deleteLink);
				deleteLink.add(new JavascriptEventConfirmation("onclick", "are you sure?"));
				item.add(deleteLink);
			}
		};
		add(eachView);
	}
	abstract protected void onDeleteItem(AjaxRequestTarget target);
	protected abstract void onEdit(AjaxRequestTarget target,JobSeekerSkill skill);

}
