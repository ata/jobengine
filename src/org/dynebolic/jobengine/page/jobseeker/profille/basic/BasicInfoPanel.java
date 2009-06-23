package org.dynebolic.jobengine.page.jobseeker.profille.basic;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class BasicInfoPanel extends BasePanel{
	private WebMarkupContainer infoContainer;
	private Component basicInfo;
	public BasicInfoPanel(String id) {
		super(id);
		infoContainer = new WebMarkupContainer("infoContainer");
		add(infoContainer);
		infoContainer.setOutputMarkupId(true);
		
		if(getJESession().getUser().getComplete()) {
			infoContainer.add(new BasicInfoViewPanel("basicInfo"){
				protected void onClickEdit(AjaxRequestTarget target) {
					basicInfo = getForm();
					infoContainer.addOrReplace(basicInfo);
					target.addComponent(infoContainer);
				}
			});
		} else {
			infoContainer.add(new BasicInfoFormPanel("basicInfo"){
				protected void onAjaxSubmit(AjaxRequestTarget target) {
					basicInfo = getView();
					infoContainer.addOrReplace(basicInfo);
					target.addComponent(infoContainer);
				}
			});
		}
		
		
		
	}
	private Component getView() {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("basicInfo"){
			public Component getLazyLoadComponent(String id) {
				return new BasicInfoViewPanel(id){
					protected void onClickEdit(AjaxRequestTarget target) {
						basicInfo = getForm();
						infoContainer.addOrReplace(basicInfo);
						target.addComponent(infoContainer);
					}
				};
			}
		};
		load.setOutputMarkupId(true);
		return load;
	}
	
	
	private Component getForm() {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("basicInfo"){
			public Component getLazyLoadComponent(String id) {
				return new BasicInfoFormPanel(id){
					protected void onAjaxSubmit(AjaxRequestTarget target) {
						basicInfo = getView();
						infoContainer.addOrReplace(basicInfo);
						target.addComponent(infoContainer);
					}
				};
			}
		};
		load.setOutputMarkupId(true);
		
		return load;
	}
}
