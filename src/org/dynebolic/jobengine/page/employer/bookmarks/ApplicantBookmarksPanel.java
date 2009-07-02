package org.dynebolic.jobengine.page.employer.bookmarks;

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
import org.dynebolic.library.JavascriptEventConfirmation;

public class ApplicantBookmarksPanel extends BasePanel {
	private DateFormat dateFormat =  DateFormat.getDateInstance(DateFormat.FULL);
	private EmployerService service = new EmployerService();
	public ApplicantBookmarksPanel(String id) {
		super(id);
		Set<JobSeeker> jobSeekerSet = getJESession().getUser().getEmployer().getBookmarks();
		List<JobSeeker> jobSeekerList = new ArrayList<JobSeeker>(jobSeekerSet);
		ListView eachView = new ListView("eachView",jobSeekerList){
			@Override
			protected void populateItem(final ListItem item) {
				item.setOutputMarkupId(true);
				final JobSeeker jobSeeker = (JobSeeker) item.getModelObject();
				final ModalWindow detailModal = new ModalWindow("detailModal");
				item.add(detailModal);
				detailModal.setInitialWidth(780);
				detailModal.setResizable(false);
				detailModal.setCssClassName("w_silver");
				
				AjaxLink detailLink = new AjaxLink("detailLink"){
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
				item.add(detailLink);
				item.add(new Label("name",jobSeeker.getName()));
				item.add(new Label("keySkills",jobSeeker.getKeySkills()));
				AjaxLink deleteLink = new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						if(getJESession().getUser().getEmployer().getBookmarks().remove(jobSeeker)){
							service.save(getJESession().getUser().getEmployer());
						}
						target.appendJavascript("$('"+item.getMarkupId()+"').addClassName('delete').fade()");
					}
					
				};
				item.add(deleteLink);
				deleteLink.add(new JavascriptEventConfirmation("onclick", "are you sure?"));
				
				final ModalWindow messageModal = new ModalWindow("messageModal");
				item.add(messageModal);
				messageModal.setInitialWidth(780);
				messageModal.setResizable(false);
				messageModal.setCssClassName("w_silver");
				messageModal.setContent(new MessagePanel(messageModal.getContentId(),jobSeeker.getUser().getId()){

					@Override
					protected void onAjaxSubmit(AjaxRequestTarget target) {
						messageModal.close(target);
						target.appendJavascript("$('messageSend').show().fade({duration:2});");
						
					}

					@Override
					protected void onCancel(AjaxRequestTarget target) {
						messageModal.close(target);
						
					}
					
				});
				AjaxLink messageLink = new AjaxLink("messageLink"){
					public void onClick(AjaxRequestTarget target) {
						messageModal.show(target);
					}
					
				};
				item.add(messageLink);
				
			}
		};
		
		add(eachView);
		
	}

}
