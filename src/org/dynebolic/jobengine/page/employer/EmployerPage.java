package org.dynebolic.jobengine.page.employer;


import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.page.employer.directory.ApplicantDirectoryPanel;
import org.dynebolic.jobengine.page.employer.search.ApplicantSearchPanel;

public class EmployerPage extends BasePage{
	
	protected Panel content;
	
	public EmployerPage() {
		menuPanel.get("applicantLink").add(new SimpleAttributeModifier("class","selected"));
		add(new ApplicantSearchPanel("searchPanel"));
		EmployerMenuPanel menu = new EmployerMenuPanel("employerMenuPanel",this);
		add(menu);
		
		menu.setVisible(false);
		if(roles.toString().equalsIgnoreCase("Employer")){
			menu.setVisible(true);
		}
		
		content = new ApplicantDirectoryPanel("content");
		content.setOutputMarkupId(true);
		add(content);

	}

}
