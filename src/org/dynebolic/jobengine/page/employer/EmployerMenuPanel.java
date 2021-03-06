package org.dynebolic.jobengine.page.employer;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.employer.bookmarks.ApplicantBookmarksPanel;
import org.dynebolic.jobengine.page.employer.directory.ApplicantDirectoryPanel;
import org.dynebolic.jobengine.page.employer.job.ClassicJobPanel;
import org.dynebolic.jobengine.page.employer.profile.ProfilePanel;
import org.dynebolic.jobengine.page.employer.submited.ApplicantSubmitedPanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class EmployerMenuPanel extends BasePanel {
	private Component content;
	
	public EmployerMenuPanel(String id, final EmployerPage page) {
		super(id);
		AjaxLink applicantDirecoryLink = new AjaxLink("applicantDirectoryLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return new ApplicantDirectoryPanel("content");
					}
				};
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(applicantDirecoryLink);
		
		AjaxLink oldJobLink = new AjaxLink("oldJobLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return  new ClassicJobPanel("content");
					}
				};
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(oldJobLink);
		
		AjaxLink submitedLink = new AjaxLink("submitedLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return  new ApplicantSubmitedPanel(id);
					}
				};
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(submitedLink);
		
		AjaxLink bookmarksLink = new AjaxLink("bookmarksLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return  new ApplicantBookmarksPanel(id);
					}
				};
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(bookmarksLink);

		AjaxLink profileLink = new AjaxLink("profileLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return  new ProfilePanel(id,page);
					}
				};
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
		};
		add(profileLink);
		
		
	}
	
	
	
}
