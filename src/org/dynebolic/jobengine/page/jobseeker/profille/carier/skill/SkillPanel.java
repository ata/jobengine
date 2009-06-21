package org.dynebolic.jobengine.page.jobseeker.profille.carier.skill;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class SkillPanel extends BasePanel{
	private WebMarkupContainer formContainer;
	private WebMarkupContainer listContainer;
	private Component form;
	private Component list;
	public SkillPanel(String id) {
		super(id);
		
		listContainer = new WebMarkupContainer("listContainer");
		listContainer.setOutputMarkupId(true);
		add(listContainer);
		//list = getListPanel();
		list = new SkillListPanel("listPanel"){
			protected void onDeleteItem(AjaxRequestTarget target) {
				list = getListPanel();
				listContainer.addOrReplace(list);
				target.addComponent(listContainer);
			}
			
		};
		listContainer.add(list);
		
		formContainer = new WebMarkupContainer("formContainer");
		formContainer.setOutputMarkupId(true);
		add(formContainer);
		form = getFormPanel();
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
	
	public Component getFormPanel() {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("formPanel"){
			public Component getLazyLoadComponent(String id) {
				return new SkillFormPanel(id){
					protected void onSubmitAjax(AjaxRequestTarget target) {
						
						list = getListPanel();
						listContainer.addOrReplace(list);
						target.addComponent(listContainer);
						
						form = getFormPanel();
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
	
	public Component getListPanel() {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("listPanel"){
			public Component getLazyLoadComponent(String id) {
				return new SkillListPanel(id){
					protected void onDeleteItem(AjaxRequestTarget target) {
						list = getListPanel();
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
