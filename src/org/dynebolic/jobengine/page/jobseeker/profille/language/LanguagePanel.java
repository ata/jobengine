package org.dynebolic.jobengine.page.jobseeker.profille.language;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.dynebolic.jobengine.entity.JobSeekerLanguage;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class LanguagePanel extends BasePanel{
	private WebMarkupContainer formContainer;
	private WebMarkupContainer listContainer;
	private Component form;
	private Component list;
	private AjaxLink addLink;
	public LanguagePanel(String id) {
		super(id);
		
		listContainer = new WebMarkupContainer("listContainer");
		listContainer.setOutputMarkupId(true);
		add(listContainer);
		//list = getListPanel();
		
		list = new LanguageListPanel("listPanel",true){
			protected void onDeleteItem(AjaxRequestTarget target) {
				list = getListPanel();
				listContainer.addOrReplace(list);
				target.addComponent(listContainer);
			}

			@Override
			protected void onEdit(AjaxRequestTarget target,
					JobSeekerLanguage language) {
				form = getFormPanel(language);
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
	
	public Component getFormPanel(final JobSeekerLanguage jsl) {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("formPanel"){
			public Component getLazyLoadComponent(String id) {
				return new LanguageFormPanel(id, jsl){
					protected void onSubmitAjax(AjaxRequestTarget target) {
						
						list = getListPanel();
						listContainer.addOrReplace(list);
						target.addComponent(listContainer);
						
						form = getFormPanel(jsl);
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
				return new LanguageListPanel(id,true){
					protected void onDeleteItem(AjaxRequestTarget target) {
						list = getListPanel();
						listContainer.addOrReplace(list);
						target.addComponent(listContainer);
					}

					@Override
					protected void onEdit(AjaxRequestTarget target,
							JobSeekerLanguage language) {
						form = getFormPanel(language);
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
