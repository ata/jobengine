package org.dynebolic.jobengine.page.auth;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.page.jobseeker.search.JobSearchPanel;

public class SignInPage extends BasePage implements IAjaxIndicatorAware{
	public SignInPage()
	{
		this(null);
	}

	public SignInPage(final PageParameters parameters) {
		menuPanel.get("signLink").add(new SimpleAttributeModifier("class","selected"));
		add(new SignInPanel("signInPanel"));
		add(new JobSearchPanel("searchPanel", this));
		add(new Label("content","Please Login"));
	}
}
