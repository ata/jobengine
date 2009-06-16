package org.dynebolic.jobengine.page.auth;

import java.io.Serializable;

import org.apache.wicket.authentication.panel.SignInPanel;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.dynebolic.jobengine.JobEngineAuthenticatedWebSession;
import org.dynebolic.jobengine.page.administrator.AdministratorPage;
import org.dynebolic.jobengine.page.employer.EmployerPage;
import org.dynebolic.jobengine.page.jobseeker.JobSeekerPage;



public class QuickSignInPanel extends SignInPanel implements Serializable{

	private static final long serialVersionUID = 1L;

	public QuickSignInPanel(String id) {
		super(id);
	}
	protected void onSignInSucceeded()
	{
		Roles roles = JobEngineAuthenticatedWebSession.get().getRoles();
		
		if (!continueToOriginalDestination())
		{
			if(roles.toString().equalsIgnoreCase("Administrator")){
				setResponsePage(AdministratorPage.class);
			} else if(roles.toString().equalsIgnoreCase("Employer")){
				setResponsePage(EmployerPage.class);
			} else {
				setResponsePage(JobSeekerPage.class);
			}
		}
	}
	
}
