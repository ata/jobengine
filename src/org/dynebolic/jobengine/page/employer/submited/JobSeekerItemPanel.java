package org.dynebolic.jobengine.page.employer.submited;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.Employer;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.employer.job.JobPreviewPanel;
import org.dynebolic.jobengine.page.jobseeker.preview.ApplicantPreviewPanel;
import org.dynebolic.jobengine.page.message.MessagePanel;
import org.dynebolic.jobengine.service.EmployerService;
import org.dynebolic.jobengine.service.JobService;
import org.dynebolic.library.JavascriptEventConfirmation;
import org.dynebolic.library.PictureViewPanel;

public abstract class JobSeekerItemPanel extends BasePanel {
	private EmployerService employerService = new EmployerService();
	private JobService jobService = new JobService();
	Employer employer;
	public JobSeekerItemPanel(String id,final Job job, final JobSeeker jobSeeker) {
		super(id);
		setModel(new CompoundPropertyModel(jobSeeker));
		add(new PictureViewPanel("photo",jobSeeker.getPhoto(), 100, 100));
		add(new Label("name"));
		add(new Label("keySkills"));
		add(new Label("currentLocation.name"));
		AjaxLink bookmarksLink = new AjaxLink("bookmarksLink"){
			public void onClick(AjaxRequestTarget target) {
				if(getJESession().getUser().getEmployer().getBookmarks().add(jobSeeker)){
					employerService.save(getJESession().getUser().getEmployer());
					job.getSubmiteds().remove(jobSeeker);
					jobService.save(job);
					onBookmarks(target);
				}
			}
			
		};
		bookmarksLink.add(new JavascriptEventConfirmation("onclick", "are you sure moving bookmarks?"));
		add(bookmarksLink);
		AjaxLink deleteLink = new AjaxLink("deleteLink"){
			public void onClick(AjaxRequestTarget target) {
				job.getSubmiteds().remove(jobSeeker);
				jobService.save(job);
				onDelete(target);
			}
			
		};
		deleteLink.add(new JavascriptEventConfirmation("onclick", "are you sure to delete this applicant?"));
		add(deleteLink);
		
		final ModalWindow detailModal = new ModalWindow("detailModal");
		add(detailModal);
		detailModal.setInitialWidth(780);
		detailModal.setResizable(false);
		detailModal.setCssClassName("w_silver");
		AjaxLink detailLink = new AjaxLink("detailLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				detailModal.setContent(new ApplicantPreviewPanel(detailModal.getContentId(),jobSeeker.getId()){

					@Override
					protected void onClose(AjaxRequestTarget target) {
						detailModal.close(target);
						
					}
					
				});
				detailModal.show(target);
			}
		};
		detailLink.add(new Label("title",jobSeeker.getTitle()));
		add(detailLink);
		
		final ModalWindow modalMessage = new ModalWindow("modalMessage");
		add(modalMessage);
		modalMessage.setContent(new MessagePanel(modalMessage.getContentId(),jobSeeker.getUser().getId()){

			@Override
			protected void onAjaxSubmit(AjaxRequestTarget target) {
				modalMessage.close(target);
				target.appendJavascript("$('messageSend').show().fade({duration:2});");
				
			}

			@Override
			protected void onCancel(AjaxRequestTarget target) {
				modalMessage.close(target);
				
			}
			
		});
		
		modalMessage.setInitialWidth(780);
		modalMessage.setResizable(false);
		modalMessage.setCssClassName("w_silver");
		
		
		
		AjaxLink messageLink = new AjaxLink("messageLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				modalMessage.show(target);
			}
		};
		add(messageLink);
	}
	protected abstract void onBookmarks(AjaxRequestTarget target);
	protected abstract void onDelete(AjaxRequestTarget target);
	
}

