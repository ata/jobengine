package org.dynebolic.jobengine.page.jobseeker.profille;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.page.BasePage;

public class ProfilePanel extends Panel{
	JobSeeker jobSeeker;
	public ProfilePanel(String id, BasePage page) {
		super(id);
		jobSeeker = page.getJESession().getUser().getJobSeeker();
		add(new Label("name",jobSeeker.getName()));
		add(new Label(""));
	}

}
