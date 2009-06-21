package org.dynebolic.jobengine.page.jobseeker.profille.basic;

import java.text.DateFormat;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.page.BasePanel;

@SuppressWarnings("serial")
public abstract class BasicInfoViewPanel extends BasePanel{
	private JobSeeker jobSeeker = getJESession().getUser().getJobSeeker();
	private DateFormat dateFormat =  DateFormat.getDateInstance(DateFormat.FULL);
	public BasicInfoViewPanel(String id) {
		super(id);
		
		System.out.println(getJESession().getUser());
		
		add(new Label("name",jobSeeker.getName()));
		add(new Label("birthPlace",jobSeeker.getBirthPlace()));
		add(new Label("birthDate",jobSeeker.getBirthDate()!=null?dateFormat.format(jobSeeker.getBirthDate()):"-"));
		
		add(new Label("gender",jobSeeker.getGender()));
		add(new Label("country",jobSeeker.getCountry()!=null?jobSeeker.getCountry().getName():"-"));
		add(new Label("currentLocation", jobSeeker.getCurrentLocation()!=null?jobSeeker.getCurrentLocation().getName():"-"));
		add(new Label("address", jobSeeker.getAddress()));
		
		add(new AjaxLink("editLink") { 
			public void onClick(AjaxRequestTarget target) {
				onClickEdit(target);
			}
		});
	}
	
	protected abstract void onClickEdit(AjaxRequestTarget target);
	
}
