package org.dynebolic.jobengine.page.jobseeker.profille.basic;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.Country;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.Location;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.jobengine.service.CountryService;
import org.dynebolic.jobengine.service.LocationService;
@SuppressWarnings("serial")
public abstract class BasicInfoFormPanel extends BasePanel{
	private LocationService locationService = new LocationService();
	private JobSeekerService jobSeekerService = new JobSeekerService();
	private CountryService countryService = new CountryService();
	private JobSeeker jobSeeker = getJESession().getUser().getJobSeeker();
	
	public BasicInfoFormPanel(String id) {
		super(id);
		
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);
		
		Form form = new Form("form",new CompoundPropertyModel(jobSeeker));
		add(form);
		
		FormComponent fc;
		
		fc = new TextField("name");
		fc.setRequired(true);
		form.add(fc);
		
		fc = new TextField("birthPlace");
		fc.setRequired(true);
		form.add(fc);
		
		fc = new DateTextField("birthDate");
		fc.setRequired(true);
		fc.add(new DatePicker());
		form.add(fc);
		
		ArrayList<String> genders = new ArrayList<String>();
		genders.add("Male");
		genders.add("Female");
		fc = new DropDownChoice("gender",genders);
		fc.setRequired(true);
		form.add(fc);
		
		List<Country> countries = countryService.find();
        fc = new DropDownChoice("country",countries,new ChoiceRenderer("name","id"));
        fc.setRequired(true);
        form.add(fc);
		
		List<Location> locations = locationService.find();
        fc = new DropDownChoice("currentLocation",locations,new ChoiceRenderer("name","id"));
        fc.setRequired(true);
        form.add(fc);
        
        fc = new TextArea("address");
        fc.setRequired(true);
        form.add(fc);
        
        AjaxButton submit = new AjaxButton("submit"){

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				jobSeeker.getUser().setName(jobSeeker.getName());
				jobSeekerService.save(jobSeeker);
				getJESession().setUser(jobSeeker.getUser());
				onAjaxSubmit(target);
				
			}
			protected void onError(AjaxRequestTarget target, Form form){
				target.addComponent(feedback);
			}
        	
        };
        form.add(submit);
		
	}
	protected abstract void onAjaxSubmit(AjaxRequestTarget target);
}
