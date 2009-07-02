package org.dynebolic.jobengine;

import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.target.coding.IndexedParamUrlCodingStrategy;
import org.dynebolic.jobengine.page.administrator.AdministratorPage;
import org.dynebolic.jobengine.page.auth.SignInPage;
import org.dynebolic.jobengine.page.jobseeker.JobSeekerPage;

public class JobEngineApplication extends AuthenticatedWebApplication{

	
	@Override
	public Class<? extends WebPage>  getHomePage() {
		// TODO Auto-generated method stub
		return JobSeekerPage.class;
		
	}
	
	@Override
	protected void init() {
		super.init();
		//addComponentInstantiationListener(new SpringComponentInjector(this));
		mount(new IndexedParamUrlCodingStrategy("/administrator", AdministratorPage.class));
	}
	
	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}
	
	@Override
	protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
		return JobEngineAuthenticatedWebSession.class;
	}
	
	public String getUploadPath(){
		return "/var/jobengine/files/";
	}
	
}
