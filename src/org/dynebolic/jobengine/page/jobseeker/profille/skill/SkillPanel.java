package org.dynebolic.jobengine.page.jobseeker.profille.skill;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.dynebolic.jobengine.entity.JobSeekerSkill;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class SkillPanel extends BasePanel{
	private WebMarkupContainer formContainer;
	private WebMarkupContainer listContainer;
	private Component form;
	private Component list;
	private AjaxLink addLink;
	public SkillPanel(String id) {
		super(id);
		
		listContainer = new WebMarkupContainer("listContainer");
		listContainer.setOutputMarkupId(true);
		add(listContainer);
		//list = getListPanel();
		
		list = new SkillListPanel("listPanel",true){
			protected void onDeleteItem(AjaxRequestTarget target) {
				list = getListPanel();
				listContainer.addOrReplace(list);
				target.addComponent(listContainer);
			}

			@Override
			protected void onEdit(AjaxRequestTarget target, JobSeekerSkill skill) {
				form = getFormPanel(skill);
				//form.setVisible(false);
				formContainer.addOrReplace(form);
				target.addComponent(formContainer);
				
			}
			
		};
		
		listContainer.add(list);
		
		formContainer = new WebMarkupContainer("formContainer");
		formContainer.setOutputMarkupId(true);
		add(formContainer);
		form = getFormPanel(null);
		form.setVisible(false);
		formContainer.add(form);
		
		addLink = getAddLink();
		add(addLink);
		
	}
	
	public Component getFormPanel(final JobSeekerSkill jss) {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("formPanel"){
			public Component getLazyLoadComponent(String id) {
				return new SkillFormPanel(id, jss){
					protected void onSubmitAjax(AjaxRequestTarget target) {
						
						list = getListPanel();
						listContainer.addOrReplace(list);
						target.addComponent(listContainer);
						
						form = getFormPanel(jss);
						form.setVisible(false);
						formContainer.addOrReplace(form);
						target.addComponent(formContainer);
					}

					@Override
					protected void onCancel(AjaxRequestTarget target) {
						form.setVisible(false);
						target.addComponent(formContainer);
						
					}
				};
			}	
		};
		load.setOutputMarkupId(true);
		return load;
		
	}
	
	public Component getListPanel() {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("listPanel"){
			public Component getLazyLoadComponent(String id) {
				return new SkillListPanel(id,true){
					protected void onDeleteItem(AjaxRequestTarget target) {
						list = getListPanel();
						listContainer.addOrReplace(list);
						target.addComponent(listContainer);
					}

					@Override
					protected void onEdit(AjaxRequestTarget target,
							JobSeekerSkill skill) {
						form = getFormPanel(skill);
						//form.setVisible(false);
						formContainer.addOrReplace(form);
						target.addComponent(formContainer);
						
					}
					
				};
			}
			
		};
		load.setOutputMarkupId(true);
		return load;
	}

	public Component getForm() {
		return form;
	}
	
	private AjaxLink getAddLink(){
		AjaxLink link = new AjaxLink("addLink"){
			public void onClick(AjaxRequestTarget target) {
				//form.setVisible(!form.isVisible());
				form = getFormPanel(null);
				formContainer.addOrReplace(form);
				target.addComponent(formContainer);
			}
		};
		link.setOutputMarkupId(true);
		return link;
	}
	
	
}
