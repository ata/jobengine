package org.dynebolic.jobengine.page.employer.job;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobService;
import org.dynebolic.library.PictureViewPanel;

@SuppressWarnings("serial")
public abstract class JobPreviewPanel extends BasePanel {
	private Job job;
	private JobService service = new JobService();
	public JobPreviewPanel(String id,Long jobId) {
		super(id);
		job = service.find(jobId);
		employer();
		add(new AjaxLink("closeLink"){
			public void onClick(AjaxRequestTarget target) {
				onClose(target);
			}
			
		});
		
	}
	protected abstract void onClose(AjaxRequestTarget target);
	private void employer() {
		setModel(new CompoundPropertyModel(job.getEmployer()));
		add(new PictureViewPanel("photo",job.getEmployer().getPhoto(), 100, 100));
		add(new Label("name"));
		add(new Label("category.name"));
		add(new Label("country.name"));
		add(new Label("location.name"));
		add(new Label("description", job.getEmployer().getDescription()
				.replaceAll("\n", "<br/>")).setEscapeModelStrings(false));
		
	}

}
