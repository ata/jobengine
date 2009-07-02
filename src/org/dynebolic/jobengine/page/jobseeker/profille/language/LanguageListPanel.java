package org.dynebolic.jobengine.page.jobseeker.profille.language;

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
import org.dynebolic.library.JavascriptEventConfirmation;

@SuppressWarnings("serial")
public abstract class LanguageListPanel extends BasePanel{
	private JobSeekerLanguageService languageService = new JobSeekerLanguageService();
	private List<JobSeekerLanguage> languages;
	
	public LanguageListPanel(String id){
		this(id,false);
	}
	
	public LanguageListPanel(String id, Boolean reload) {
		super(id);
		if(reload) {
			languages = languageService.find(getJESession().getUser().getJobSeeker());
		} else {
			languages = getJESession().getUser().getJobSeeker().getLanguages();
		}
		final ListView eachView = new ListView("eachView", languages){
			protected void populateItem(final ListItem item) {
				final JobSeekerLanguage language = (JobSeekerLanguage) item.getModelObject();
				item.setModel(new CompoundPropertyModel(language));
				item.setOutputMarkupId(true);
				item.add(new Label("language.name"));
				item.add(new Label("level"));
				
				AjaxLink editLink = new AjaxLink("editLink"){
					public void onClick(AjaxRequestTarget target) {
						onEdit(target, language);
					}
					
				};
				
				item.add(editLink);
				
				AjaxLink deleteLink = new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						languageService.remove(language);
						target.appendJavascript("$('"+item.getMarkupId()+"').addClassName('delete').fade()");
						//onDeleteItem(target);
					}
				};
				deleteLink.add(new JavascriptEventConfirmation("onclick", "are you sure?"));
				item.add(deleteLink);
			}
		};
		add(eachView);
	}
	abstract protected void onDeleteItem(AjaxRequestTarget target);
	protected abstract void onEdit(AjaxRequestTarget target,JobSeekerLanguage language);

}
