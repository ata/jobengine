package org.dynebolic.jobengine.page.employer.profille.general;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class GeneralInfoPanel extends BasePanel{
	private WebMarkupContainer infoContainer;
	private Component generalInfo;
	public GeneralInfoPanel(String id) {
		super(id);
		infoContainer = new WebMarkupContainer("infoContainer");
		add(infoContainer);
		infoContainer.setOutputMarkupId(true);
		
		if(getJESession().getUser().getComplete()) {
			infoContainer.add(new GeneralInfoViewPanel("generalInfo"){
				protected void onClickEdit(AjaxRequestTarget target) {
					generalInfo = getForm();
					infoContainer.addOrReplace(generalInfo);
					target.addComponent(infoContainer);
				}
			});
		} else {
			infoContainer.add(new GeneralInfoFormPanel("generalInfo"){
				protected void onAjaxSubmit(AjaxRequestTarget target) {
					generalInfo = getView();
					infoContainer.addOrReplace(generalInfo);
					target.addComponent(infoContainer);
				}
			});
		
		}
		
		
		
	}
	private Component getView() {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("generalInfo"){
			public Component getLazyLoadComponent(String id) {
				return new GeneralInfoViewPanel(id){
					protected void onClickEdit(AjaxRequestTarget target) {
						generalInfo = getForm();
						infoContainer.addOrReplace(generalInfo);
						target.addComponent(infoContainer);
					}
				};
			}
		};
		load.setOutputMarkupId(true);
		return load;
	}
	
	
	private Component getForm() {
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("generalInfo"){
			public Component getLazyLoadComponent(String id) {
				return new GeneralInfoFormPanel(id){
					protected void onAjaxSubmit(AjaxRequestTarget target) {
						generalInfo = getView();
						infoContainer.addOrReplace(generalInfo);
						target.addComponent(infoContainer);
					}
				};
			}
		};
		load.setOutputMarkupId(true);
		
		return load;
	}
}
