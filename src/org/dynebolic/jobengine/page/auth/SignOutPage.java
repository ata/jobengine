package org.dynebolic.jobengine.page.auth;

import org.apache.wicket.PageParameters;
import org.dynebolic.jobengine.page.BasePage;

public class SignOutPage extends BasePage{
	public SignOutPage()
	{
		this(null);
	}
	public SignOutPage(final PageParameters parameters) {
		getSession().invalidate();
		
	}
}
