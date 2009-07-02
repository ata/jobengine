package org.dynebolic.jobengine.page.jobseeker;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.page.auth.SignInPanel;
import org.dynebolic.jobengine.page.auth.SignUpPanel;
import org.dynebolic.jobengine.page.jobseeker.directory.JobDirectoryPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.ProfilePanel;
import org.dynebolic.jobengine.page.jobseeker.search.JobSearchPanel;
import org.dynebolic.library.AjaxLazyLoadPanel;
import org.dynebolic.library.PictureViewPanel;

@SuppressWarnings("serial")

public class JobSeekerPage extends BasePage{
	protected Panel content;
	private WebMarkupContainer guestContainer;
	
	public JobSeekerPage() {
		menuPanel.get("jobLink").add(new SimpleAttributeModifier("class","selected"));
		add(new JobSearchPanel("searchPanel", this));
		// content
		content = new JobDirectoryPanel("content");
		content.setOutputMarkupId(true);
		add(content);
		
		add(new Label("photo",""));
		JobSeekerMenuPanel menu = 
			new JobSeekerMenuPanel("jobSeekerMenuPanel",this);
		add(menu);
		menu.setVisible(false);
		
		guestContainer = new WebMarkupContainer("guestContainer");
		add(guestContainer);
		guestContainer.setVisible(true);
		guestContainer.setOutputMarkupId(true);
		guestContainer.add(new SignInPanel("signInPanel"));
		guestContainer.add(new AjaxLink("signUpLink"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return new SignUpPanel(id, JobSeekerPage.this);
					}
				};
				//content = new SignUpPanel("content");
				content.setOutputMarkupId(true);
				JobSeekerPage.this.addOrReplace(content);
				target.addComponent(content);
				
			}
			
		});
		
		
		
		if(!getJESession().getRoles().toString().equalsIgnoreCase("Guest")) {
			guestContainer.setVisible(false);
		}
		
		if(getJESession().getRoles().toString().equalsIgnoreCase("JobSeeker")) {
			menu.setVisible(true);
			addOrReplace(new PictureViewPanel("photo",getJESession().getUser().getJobSeeker().getPhoto(), 210, 210));
			
			if(!getJESession().getUser().getComplete()) {
				content = new ProfilePanel("content", this);
				content.setOutputMarkupId(true);
				addOrReplace(content);
			}
		}
		
	}
	
	
}
