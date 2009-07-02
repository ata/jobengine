package org.dynebolic.jobengine.page.jobseeker.profille.carier;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.dynebolic.jobengine.entity.JobSeekerCarier;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class CarierPanel extends BasePanel{
	private WebMarkupContainer formContainer;
	private WebMarkupContainer listContainer;
	private Component form;
	private Component list;
	private AjaxLink addLink;
	public CarierPanel(String id) {
		super(id);
		
		listContainer = new WebMarkupContainer("listContainer");
		listContainer.setOutputMarkupId(true);
		add(listContainer);
		//list = getListPanel();
		
		list = new CarierListPanel("listPanel",true){
			protected void onDeleteItem(AjaxRequestTarget target) {
				list = getListPanel();
				listContainer.addOrReplace(list);
				target.addComponent(listContainer);
			}

			@Override
			protected void onEdit(AjaxRequestTarget target,
					JobSeekerCarier carier) {
				form = getFormPanel(carier);
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
	
	public Component getFormPanel(final JobSeekerCarier jsc) {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("formPanel"){
			public Component getLazyLoadComponent(String id) {
				return new CarierFormPanel(id, jsc){
					protected void onSubmitAjax(AjaxRequestTarget target) {
						list = getListPanel();
						listContainer.addOrReplace(list);
						target.addComponent(listContainer);
						
						form = getFormPanel(jsc);
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
				return new CarierListPanel(id,true){
					protected void onDeleteItem(AjaxRequestTarget target) {
						list = getListPanel();
						listContainer.addOrReplace(list);
						target.addComponent(listContainer);
					}

					@Override
					protected void onEdit(AjaxRequestTarget target,
							JobSeekerCarier carier) {
						form = getFormPanel(carier);
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
				form = getFormPanel(null);
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
