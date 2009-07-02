package org.dynebolic.jobengine.page.jobseeker.profille.general;

import java.text.DateFormat;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.page.BasePanel;

@SuppressWarnings("serial")
public abstract class GeneralInfoViewPanel extends BasePanel{
	private JobSeeker jobSeeker = getJESession().getUser().getJobSeeker();
	private DateFormat dateFormat =  DateFormat.getDateInstance(DateFormat.FULL);
	public GeneralInfoViewPanel(String id) {
		super(id);
		setModel(new CompoundPropertyModel(jobSeeker));
		add(new Label("name"));
		add(new Label("birthPlace"));
		add(new Label("birthDate",jobSeeker.getBirthDate()!=null?dateFormat.format(jobSeeker.getBirthDate()):"-"));
		add(new Label("religion.name"));
		
		add(new Label("gender",jobSeeker.getGender()));
		add(new Label("country.name"));
		add(new Label("currentLocation.name"));
		add(new Label("address"));
		
		add(new Label("phone"));
		add(new Label("cellular"));
		add(new Label("personalWeb"));
		
		add(new Label("title"));
		add(new Label("category.name"));
		add(new Label("preferPosition"));
		add(new Label("keySkills"));
		
		add(new Label("description", jobSeeker.getDescription()
				.replaceAll("\n", "<br/>")).setEscapeModelStrings(false));
		
		add(new AjaxLink("editLink") { 
			public void onClick(AjaxRequestTarget target) {
				onClickEdit(target);
			}
		});
	}
	
	protected abstract void onClickEdit(AjaxRequestTarget target);
	
}
