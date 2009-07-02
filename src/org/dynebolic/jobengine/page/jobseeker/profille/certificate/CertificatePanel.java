package org.dynebolic.jobengine.page.jobseeker.profille.certificate;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.dynebolic.jobengine.entity.JobSeekerCertificate;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class CertificatePanel extends BasePanel{
	private WebMarkupContainer formContainer;
	private WebMarkupContainer listContainer;
	private Component form;
	private Component list;
	private AjaxLink addLink;
	public CertificatePanel(String id) {
		super(id);
		
		listContainer = new WebMarkupContainer("listContainer");
		listContainer.setOutputMarkupId(true);
		add(listContainer);
		//list = getListPanel();
		
		
		list = new CertificateListPanel("listPanel",true){
			protected void onDeleteItem(AjaxRequestTarget target) {
				list = getListPanel();
				listContainer.addOrReplace(list);
				target.addComponent(listContainer);
			}

			@Override
			protected void onEdit(AjaxRequestTarget target,
					JobSeekerCertificate certificate) {
				form = getFormPanel(certificate);
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
	
	public Component getFormPanel(final JobSeekerCertificate jsc) {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("formPanel"){
			public Component getLazyLoadComponent(String id) {
				return new CertificateFormPanel(id, jsc){
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
				return new CertificateListPanel(id,true){

					@Override
					protected void onDeleteItem(AjaxRequestTarget target) {
						list = getListPanel();
						listContainer.addOrReplace(list);
						target.addComponent(listContainer);
					}

					@Override
					protected void onEdit(AjaxRequestTarget target,
							JobSeekerCertificate certificate) {
						form = getFormPanel(certificate);
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
