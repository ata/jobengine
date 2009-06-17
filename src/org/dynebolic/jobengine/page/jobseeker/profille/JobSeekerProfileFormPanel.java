package org.dynebolic.jobengine.page.jobseeker.profille;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.Location;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.service.LocationService;

public class JobSeekerProfileFormPanel extends Panel{
	
	private JobSeeker jobSeeker;
	private LocationService locationService = new LocationService();
	
	public JobSeekerProfileFormPanel(String id, BasePage page) {
		super(id);
		jobSeeker = new JobSeeker();
		jobSeeker.setName(page.getJESession().getUser().getName());
		Form form = new Form("form",new CompoundPropertyModel(jobSeeker));
		add(form);
		
		FormComponent fc;
		
		fc = new TextField("name");
		fc.setRequired(true);
		form.add(fc);
		
		fc = new TextField("birthPlace");
		fc.setRequired(true);
		form.add(fc);
		
		fc = new TextField("birthDate");
		fc.setRequired(true);
		fc.add(new DatePicker());
		form.add(fc);
		
		ArrayList<String> genders = new ArrayList<String>();
		genders.add("Male");
		genders.add("Female");
		fc = new DropDownChoice("gender",genders);
		fc.setRequired(true);
		form.add(fc);
		
		List<Location> locations = locationService.find();
        fc = new DropDownChoice("location",locations,new ChoiceRenderer("name","id"));
        fc.setRequired(true);
        form.add(fc);
        
        
		
	}
	
}
