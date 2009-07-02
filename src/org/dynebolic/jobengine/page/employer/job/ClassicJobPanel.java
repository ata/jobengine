package org.dynebolic.jobengine.page.employer.job;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.markup.html.panel.Panel;

public class ClassicJobPanel extends Panel{

	public ClassicJobPanel(String id) {
		super(id);
		add(new JobMasterGridPanel("grid"));
	}

}
