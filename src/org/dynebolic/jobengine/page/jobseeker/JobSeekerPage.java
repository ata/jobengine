package org.dynebolic.jobengine.page.jobseeker;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.page.auth.SignInPage;
import org.dynebolic.jobengine.page.auth.SignUpPanel;
import org.dynebolic.jobengine.page.jobseeker.directory.JobDirectoryPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.ProfilePanel;
import org.dynebolic.jobengine.page.jobseeker.search.JobSearchPanel;

public class JobSeekerPage extends BasePage{
	protected Panel content;
	
	public JobSeekerPage() {
		menuPanel.get("jobLink").add(new SimpleAttributeModifier("class","selected"));
		add(new JobSearchPanel("searchPanel", this));
		// content
		content = new JobDirectoryPanel("content");
		content.setOutputMarkupId(true);
		add(content);
		
		// menu
		JobSeekerMenuPanel menu = 
			new JobSeekerMenuPanel("jobSeekerMenuPanel",this);
		add(menu);
		menu.setVisible(false);
		
		// sign up box container
		WebMarkupContainer signUpContainer = new WebMarkupContainer("signUpContainer");
		add(signUpContainer);
		signUpContainer.setVisible(true);
		
		signUpContainer.add(new PageLink("loginLink",SignInPage.class));
		
		signUpContainer.add( new SignUpPanel("signUpPanel"));
		
		if(!getJESession().getRoles().toString().equalsIgnoreCase("Guest")) {
			signUpContainer.setVisible(false);
		}
		
		if(getJESession().getRoles().toString().equalsIgnoreCase("JobSeeker")) {
			menu.setVisible(true);
			
			if(!getJESession().getUser().getComplete()) {
				addOrReplace(new ProfilePanel("content"));
				//menu.setVisible(false);
			}
		}
		
	}
	
	
}
