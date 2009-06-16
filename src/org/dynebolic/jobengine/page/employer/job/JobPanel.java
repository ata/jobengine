package org.dynebolic.jobengine.page.employer.job;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.page.administrator.job.JobMasterGridPanel;

public class JobPanel extends Panel{

	public JobPanel(String id) {
		super(id);
		add(new JobMasterGridPanel("grid"));
	}

}
