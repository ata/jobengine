package org.dynebolic.jobengine.page.jobseeker;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.page.jobseeker.directory.JobDirectoryPanel;

@SuppressWarnings("serial")
public class JobSeekerMenuPanel extends Panel {
	private Panel content;
	
	public JobSeekerMenuPanel(String id, final JobSeekerPage page) {
		super(id);
		AjaxLink applicantDirecoryLink = new AjaxLink("jobDirectoryLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				content = new JobDirectoryPanel("content");
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(applicantDirecoryLink);
		
	}
	
	
	
}
