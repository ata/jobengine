package org.dynebolic.jobengine.page.jobseeker.bookmarks;

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
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.library.JavascriptEventConfirmation;

public class JobBookmarksPanel extends BasePanel {
	private DateFormat dateFormat =  DateFormat.getDateInstance(DateFormat.FULL);
	private JobSeekerService service = new JobSeekerService();
	public JobBookmarksPanel(String id) {
		super(id);
		Set<Job> jobSet = getJESession().getUser().getJobSeeker().getBookmarks();
		List<Job> jobList = new ArrayList<Job>(jobSet);
		ListView eachView = new ListView("eachView",jobList){
			@Override
			protected void populateItem(final ListItem item) {
				item.setOutputMarkupId(true);
				final Job job = (Job) item.getModelObject();
				final ModalWindow detailModal = new ModalWindow("detailModal");
				item.add(detailModal);
				detailModal.setInitialWidth(780);
				detailModal.setResizable(false);
				detailModal.setCssClassName("w_silver");
				
				AjaxLink detailLink = new AjaxLink("detailLink"){
					public void onClick(AjaxRequestTarget target) {
						detailModal.setContent(new JobPreviewPanel(detailModal.getContentId(), job.getId()){
							
							@Override
							protected void onClose(AjaxRequestTarget target) {
								detailModal.close(target);
								
							}
							
						});
						detailModal.show(target);
					}
				};
				detailLink.add(new Label("title",job.getTitle()));
				item.add(detailLink);
				item.add(new Label("expired",dateFormat.format(job.getExpired())));
				AjaxLink deleteLink = new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						if(getJESession().getUser().getJobSeeker().getBookmarks().remove(job)){
							service.save(getJESession().getUser().getJobSeeker());
						}
						target.appendJavascript("$('"+item.getMarkupId()+"').addClassName('delete').fade()");
					}
					
				};
				item.add(deleteLink);
				deleteLink.add(new JavascriptEventConfirmation("onclick", "are you sure?"));
			}
		};
		
		add(eachView);
		
	}

}
