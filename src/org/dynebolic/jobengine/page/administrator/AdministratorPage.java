package org.dynebolic.jobengine.page.administrator;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.link.Link;
import org.dynebolic.jobengine.entity.Country;
import org.dynebolic.jobengine.entity.EducationLevel;
import org.dynebolic.jobengine.entity.JobCategory;
import org.dynebolic.jobengine.entity.Language;
import org.dynebolic.jobengine.entity.Location;
import org.dynebolic.jobengine.entity.Religion;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.page.administrator.generic.GenericMasterPage;
import org.dynebolic.jobengine.page.administrator.group.GroupPage;
import org.dynebolic.jobengine.page.administrator.profile.ProfilePage;
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
		add(new Link("countryLink"){
			@Override
			public void onClick() {
				setResponsePage(new GenericMasterPage<Country>(Country.class));
			}
			
		});
		add(new Link("languageLink"){
			@Override
			public void onClick() {
				setResponsePage(new GenericMasterPage<Language>(Language.class));
			}
			
		});
		add(new Link("regionLink"){
			@Override
			public void onClick() {
				setResponsePage(new GenericMasterPage<Religion>(Religion.class));
			}
			
		});
		add(new Link("educationLink"){
			@Override
			public void onClick() {
				setResponsePage(new GenericMasterPage<EducationLevel>(EducationLevel.class));
			}
			
		});
		add(new Link("link_master_test"){
			@Override
			public void onClick() {
				setResponsePage(new GroupPage());
			}
			
		});
		add(new Link("profileLink"){
			@Override
			public void onClick() {
				setResponsePage(new ProfilePage());
			}
			
		});
	}
}
