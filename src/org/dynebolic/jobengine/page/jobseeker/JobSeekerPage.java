package org.dynebolic.jobengine.page.jobseeker;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.page.auth.SignInPage;
import org.dynebolic.jobengine.page.auth.SignUpPanel;
import org.dynebolic.jobengine.page.jobseeker.directory.JobDirectoryPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.ProfilePanel;
import org.dynebolic.jobengine.page.jobseeker.search.JobSearchPanel;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.jobengine.service.UserService;

public class JobSeekerPage extends BasePage implements IAjaxIndicatorAware{
	protected Panel content;
	private JobSeekerService jobSeekerService = new JobSeekerService();
	private UserService userService = new UserService();
	
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
		if(!roles.toString().equalsIgnoreCase("Guest")) {
			signUpContainer.setVisible(false);
		}
		
		if(roles.toString().equalsIgnoreCase("JobSeeker")) {
			menu.setVisible(true);
			
			if(getJESession().getUser().getJobSeeker() == null) {
				JobSeeker jobSeeker = new JobSeeker();
				jobSeeker.setUser(getJESession().getUser());
				jobSeeker.setName(getJESession().getUser().getName());
				jobSeekerService.save(jobSeeker);
				getJESession().setUser(userService.find(getJESession().getUser().getId()));
			}
			if(!getJESession().getUser().getComplete()) {
				addOrReplace(new ProfilePanel("content", this));
				menu.setVisible(false);
			}
		}
		
	}
	
	
}
