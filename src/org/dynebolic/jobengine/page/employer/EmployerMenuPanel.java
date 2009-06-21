package org.dynebolic.jobengine.page.employer;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.employer.directory.ApplicantDirectoryPanel;
import org.dynebolic.jobengine.page.employer.job.JobPanel;

@SuppressWarnings("serial")
public class EmployerMenuPanel extends BasePanel {
	private Panel content;
	
	public EmployerMenuPanel(String id, final EmployerPage page) {
		super(id);
		AjaxLink applicantDirecoryLink = new AjaxLink("applicantDirectoryLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				content = new ApplicantDirectoryPanel("content");
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(applicantDirecoryLink);
		
		AjaxLink jobLink = new AjaxLink("jobLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				content = new JobPanel("content");
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(jobLink);
		
	}
	
	
	
}
