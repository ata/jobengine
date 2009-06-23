package org.dynebolic.jobengine.page.jobseeker.profille.experience.education;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class EducationPanel extends BasePanel{
	private WebMarkupContainer formContainer;
	private WebMarkupContainer listContainer;
	private Component form;
	private Component list;
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
		};
		
		listContainer.add(list);
		
		formContainer = new WebMarkupContainer("formContainer");
		formContainer.setOutputMarkupId(true);
		add(formContainer);
		form = getEduationFormPanel();
		form.setVisible(false);
		formContainer.add(form);
		
		AjaxLink addLink = new AjaxLink("addLink"){
			public void onClick(AjaxRequestTarget target) {
				form.setVisible(!form.isVisible());
				target.addComponent(formContainer);
			}
		};
		add(addLink);
		
	}
	
	public Component getEduationFormPanel() {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("educationFormPanel"){
			public Component getLazyLoadComponent(String id) {
				return new EducationFormPanel(id){
					protected void onSubmitAjax(AjaxRequestTarget target) {
						
						list = getEducationListPanel();
						listContainer.addOrReplace(list);
						target.addComponent(listContainer);
						
						form = getEduationFormPanel();
						form.setVisible(false);
						formContainer.addOrReplace(form);
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
					
				};
			}
			
		};
		load.setOutputMarkupId(true);
		return load;
	}

	public Component getForm() {
		return form;
	}
	
	
	
	
}
