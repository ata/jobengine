package org.dynebolic.jobengine.page.jobseeker;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.page.jobseeker.directory.JobDirectoryPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.ProfilePanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class JobSeekerMenuPanel extends Panel {
	private Component content;
	
	public JobSeekerMenuPanel(String id, final JobSeekerPage page) {
		super(id);
		AjaxLink applicantDirecoryLink = new AjaxLink("jobDirectoryLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				content = new AjaxLazyLoadPanel("content"){

					@Override
					public Component getLazyLoadComponent(String id) {
						// TODO Auto-generated method stub
						return new JobDirectoryPanel(id);
					}
					
				};
				//content = new JobDirectoryPanel("content");
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(applicantDirecoryLink);
		AjaxLink profileLink = new AjaxLink("profileLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return  new ProfilePanel(id,page);
					}
				};
				//content = new ProfilePanel("content");
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(profileLink);
	}
	
	
	
}
