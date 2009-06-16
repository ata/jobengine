package org.dynebolic.jobengine.page;

import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.JobEngineAuthenticatedWebSession;
import org.dynebolic.jobengine.page.administrator.AdministratorPage;
import org.dynebolic.jobengine.page.auth.SignInPage;
import org.dynebolic.jobengine.page.employer.EmployerPage;
import org.dynebolic.jobengine.page.jobseeker.JobSeekerPage;

public class MenuPanel extends Panel{

	private static final long serialVersionUID = 3951610949805751762L;

	@SuppressWarnings("serial")
	public MenuPanel(String id) {
		super(id);
		Roles roles = JobEngineAuthenticatedWebSession.get().getRoles();
		
		add(new PageLink("jobLink",JobSeekerPage.class));
		add(new PageLink("applicantLink",EmployerPage.class)); 
		
		PageLink administratorLink = new PageLink("administratorLink",
				AdministratorPage.class);
		add(administratorLink);
		administratorLink.setVisible(false);
		
		Link signInLink = new Link("signLink"){
			@Override
			public void onClick() {
				getSession().invalidate();
				setResponsePage(SignInPage.class);
			}
		};
		signInLink.add(new Label("signLabel","Sign In"));
		
		Link signOutLink = new Link("signLink"){
			@Override
			public void onClick() {
				getSession().invalidate();
				setResponsePage(SignInPage.class);
			}
		};
		signOutLink.add(new Label("signLabel","Sign Out"));
		
		if(roles.toString().equalsIgnoreCase("Guest")){
			add(signInLink);
		} else {
			add(signOutLink);
		}
		
		if(roles.toString().equalsIgnoreCase("Administrator")){
			administratorLink.setVisible(true);
		}
	
	}

}
