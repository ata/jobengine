package org.dynebolic.jobengine.page.jobseeker.profille.education;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.dynebolic.jobengine.entity.JobSeekerEducation;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class EducationPanel extends BasePanel{
	private WebMarkupContainer formContainer;
	private WebMarkupContainer listContainer;
	private Component form;
	private Component list;
	private AjaxLink addLink;
	public EducationPanel(String id) {
		super(id);
		
		listContainer = new WebMarkupContainer("listContainer");
		listContainer.setOutputMarkupId(true);
		add(listContainer);
		
		//list = getEducationListPanel();
		
		list = new EducationListPanel("educationListPanel",true){
			protected void onDeleteItem(AjaxRequestTarget target) {
				list = getEducationListPanel();
				listContainer.addOrReplace(list);
				target.addComponent(listContainer);
			}

			@Override
			protected void onEdit(AjaxRequestTarget target,
					JobSeekerEducation jse) {
				form = getEduationFormPanel(jse);
				//form.setVisible(false);
				formContainer.addOrReplace(form);
				target.addComponent(formContainer);
				
			}
		};
		
		listContainer.add(list);
		
		formContainer = new WebMarkupContainer("formContainer");
		formContainer.setOutputMarkupId(true);
		add(formContainer);
		form = getEduationFormPanel(null);
		form.setVisible(false);
		formContainer.add(form);
		
		addLink = getAddLink();
		add(addLink);
		
	}
	
	public Component getEduationFormPanel(final JobSeekerEducation jse) {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("educationFormPanel"){
			public Component getLazyLoadComponent(String id) {
				return new EducationFormPanel(id,jse){
					protected void onSubmitAjax(AjaxRequestTarget target) {
						
						list = getEducationListPanel();
						listContainer.addOrReplace(list);
						target.addComponent(listContainer);
						form.setVisible(false);
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
	
	public Component getEducationListPanel() {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("educationListPanel"){
			public Component getLazyLoadComponent(String id) {
				return new EducationListPanel(id,true){

					@Override
					protected void onDeleteItem(AjaxRequestTarget target) {
						list = getEducationListPanel();
						listContainer.addOrReplace(list);
						target.addComponent(listContainer);
					}

					@Override
					protected void onEdit(AjaxRequestTarget target,
							JobSeekerEducation jse) {
						form = getEduationFormPanel(jse);
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
	
	private AjaxLink getAddLink(){
		AjaxLink link = new AjaxLink("addLink"){
			public void onClick(AjaxRequestTarget target) {
				//form.setVisible(!form.isVisible());
				form = getEduationFormPanel(null);
				formContainer.addOrReplace(form);
				target.addComponent(formContainer);
			}
		};
		link.setOutputMarkupId(true);
		return link;
	}
	
	public Component getForm() {
		return form;
	}
	
	
	
	
	
}
