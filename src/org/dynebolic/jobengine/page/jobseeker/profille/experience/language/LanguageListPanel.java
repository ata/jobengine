package org.dynebolic.jobengine.page.jobseeker.profille.experience.language;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.JobSeekerLanguage;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerLanguageService;

@SuppressWarnings("serial")
public abstract class LanguageListPanel extends BasePanel{
	private JobSeekerLanguageService languageService = new JobSeekerLanguageService();
	private List<JobSeekerLanguage> languages;
	public LanguageListPanel(String id) {
		super(id);
		languages = languageService.find(getJESession().getUser().getJobSeeker());
		final ListView eachView = new ListView("eachView", languages){
			protected void populateItem(final ListItem item) {
				final JobSeekerLanguage language = (JobSeekerLanguage) item.getModelObject();
				item.setModel(new CompoundPropertyModel(language));
				item.add(new Label("language.name"));
				item.add(new Label("level"));
				item.add(new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						languageService.remove(language);
						onDeleteItem(target);
					}
					
				});
			}
		};
		add(eachView);
	}
	abstract protected void onDeleteItem(AjaxRequestTarget target);

}
