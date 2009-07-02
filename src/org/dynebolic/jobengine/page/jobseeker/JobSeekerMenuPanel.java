package org.dynebolic.jobengine.page.jobseeker;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.jobseeker.bookmarks.JobBookmarksPanel;
import org.dynebolic.jobengine.page.jobseeker.directory.JobDirectoryPanel;
import org.dynebolic.jobengine.page.jobseeker.inbox.InboxPanel;
import org.dynebolic.jobengine.page.jobseeker.preview.ApplicantPreviewPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.ProfilePanel;
import org.dynebolic.jobengine.service.MessageService;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class JobSeekerMenuPanel extends BasePanel {
	private Component content;
	
	public JobSeekerMenuPanel(String id, final JobSeekerPage page) {
		super(id);
		AjaxLink applicantDirecoryLink = new AjaxLink("jobDirectoryLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				content = new AjaxLazyLoadPanel("content"){

					@Override
					public Component getLazyLoadComponent(String id) {
						// TODO Auto-generated method stub
						return new JobDirectoryPanel(id);
					}
					
				};
				//content = new JobDirectoryPanel("content");
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(applicantDirecoryLink);
		
		
		AjaxLink profileLink = new AjaxLink("profileLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return  new ProfilePanel(id,page);
					}
				};
				//content = new ProfilePanel("content");
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(profileLink);
		
		
		AjaxLink inboxLink = new AjaxLink("inboxLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return  new InboxPanel(id);
					}
				};
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(inboxLink);
		MessageService messageService = new MessageService();
		String unread = "";
		if(getJESession().getUser() != null){
			unread = messageService.unreadCount(getJESession().getUser()).toString();
		}
		
		inboxLink.add(new Label("unread","("+unread+")"));
		
		AjaxLink bookmarksLink = new AjaxLink("bookmarksLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return  new JobBookmarksPanel(id);
					}
				};
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(bookmarksLink);
	}
	
	
	
}
