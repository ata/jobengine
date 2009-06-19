package org.dynebolic.jobengine.page.jobseeker.profille;

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
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.Location;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.jobengine.service.LocationService;
import org.dynebolic.jobengine.service.UserService;

public class BasicProfileFormPanel extends Panel{
	
	private JobSeeker jobSeeker;
	private LocationService locationService = new LocationService();
	private JobSeekerService jobSeekerService = new JobSeekerService();
	private UserService userService = new UserService();
	
	public BasicProfileFormPanel(String id, final BasePage page) {
		super(id);
		jobSeeker = page.getJESession().getUser().getJobSeeker();
		//jobSeeker.setName(page.getJESession().getUser().getName());
		
		
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
				userService.save(jobSeeker.getUser());
				target.addComponent(feedback);
				
			}
			protected void onError(AjaxRequestTarget target, Form form){
				target.addComponent(feedback);
			}
        	
        };
        form.add(submit);
		
	}
	
}
