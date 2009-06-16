package org.dynebolic.jobengine.page.jobseeker.submit;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.page.BasePage;

public class JobSeekerSubmitPanel extends Panel {

	public JobSeekerSubmitPanel(String id, BasePage page, Job job) {
		super(id);
		add(new Label("position",job.getPosition()));
	}
	
}
