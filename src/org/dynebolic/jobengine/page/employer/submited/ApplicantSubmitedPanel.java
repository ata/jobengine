package org.dynebolic.jobengine.page.employer.submited;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.employer.job.JobPreviewPanel;
import org.dynebolic.jobengine.page.jobseeker.preview.ApplicantPreviewPanel;
import org.dynebolic.jobengine.page.message.MessagePanel;
import org.dynebolic.jobengine.service.EmployerService;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.jobengine.service.JobService;
import org.dynebolic.library.JavascriptEventConfirmation;

public class ApplicantSubmitedPanel extends BasePanel {
	private JobService jobService = new JobService();
	public ApplicantSubmitedPanel(String id) {
		super(id);
		
		List<Job> jobs = jobService.findSubmit(getJESession().getUser().getEmployer()); 
		
		ListView eachJob = new ListView("eachJob",jobs){

			@Override
			protected void populateItem(ListItem jobItem) {
				final Job job = (Job) jobItem.getModelObject();
				final ModalWindow detailModal = new ModalWindow("detailModal");
				jobItem.add(detailModal);
				detailModal.setInitialWidth(780);
				detailModal.setResizable(false);
				detailModal.setCssClassName("w_silver");
				
				AjaxLink jobDetailLink = new AjaxLink("jobDetailLink"){
					public void onClick(AjaxRequestTarget target) {
						detailModal.setContent(new JobPreviewPanel(detailModal.getContentId(), job.getId()){
							protected void onClose(AjaxRequestTarget target) {
								detailModal.close(target);
							}
						});
						detailModal.show(target);
					}
				};
				jobItem.add(jobDetailLink);
				jobDetailLink.add(new Label("jobTitle",job.getTitle()));
				
				List<JobSeeker> jobSeekers = new ArrayList<JobSeeker>(job.getSubmiteds());
				ListView eachJobSeeker = new ListView("eachJobSeeker",jobSeekers){

					@Override
					protected void populateItem(final ListItem item) {
						JobSeeker jobSeeker = (JobSeeker) item.getModelObject();
						item.setOutputMarkupId(true);
						item.add(new JobSeekerItemPanel("jsItem", job, jobSeeker){

							@Override
							protected void onBookmarks(AjaxRequestTarget target) {
								target.appendJavascript("$('"+item.getMarkupId()+"').fade();" +
										"$('successBookmarks').show().fade({duration:2});");
							}

							@Override
							protected void onDelete(AjaxRequestTarget target) {
								target.appendJavascript("$('"+item.getMarkupId()+"').addClassName('delete').fade()");
							}
							
						});
						
					}
					
				};
				jobItem.add(eachJobSeeker);
				
			}
			
		};
		add(eachJob);
		
	}

}
