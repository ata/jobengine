package org.dynebolic.jobengine.page.administrator;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.link.Link;
import org.dynebolic.jobengine.entity.Education;
import org.dynebolic.jobengine.entity.JobCategory;
import org.dynebolic.jobengine.entity.Location;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.page.administrator.generic.GenericMasterPage;
import org.dynebolic.jobengine.page.administrator.group.GroupPage;
import org.dynebolic.jobengine.page.administrator.job.JobMasterPage;
import org.dynebolic.jobengine.page.jobseeker.search.JobSearchPanel;

@AuthorizeInstantiation("Administrator")
public class AdministratorPage extends BasePage{
	
	@SuppressWarnings("serial")
	public AdministratorPage(){
		menuPanel.get("administratorLink").add(new SimpleAttributeModifier("class","selected"));
		add(new JobSearchPanel("searchPanel", this));
		add(new Link("jobCategoryLink"){
			@Override
			public void onClick() {
				setResponsePage(new GenericMasterPage<JobCategory>(JobCategory.class));
			}
			
		});
		add(new Link("locationLink"){
			@Override
			public void onClick() {
				setResponsePage(new GenericMasterPage<Location>(Location.class));
			}
			
		});
		add(new Link("educationLink"){
			@Override
			public void onClick() {
				setResponsePage(new GenericMasterPage<Education>(Education.class));
			}
			
		});
		add(new Link("link_master_test"){
			@Override
			public void onClick() {
				setResponsePage(new GroupPage());
			}
			
		});
		add(new Link("jobMasterLink"){
			@Override
			public void onClick() {
				setResponsePage(new JobMasterPage());
			}
			
		});
	}
}