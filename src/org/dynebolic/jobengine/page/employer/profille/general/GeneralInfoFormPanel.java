package org.dynebolic.jobengine.page.employer.profille.general;

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
import org.dynebolic.jobengine.entity.Employer;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.Location;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.EmployerService;
import org.dynebolic.jobengine.service.JobCategoryService;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.jobengine.service.CountryService;
import org.dynebolic.jobengine.service.LocationService;
import org.dynebolic.jobengine.service.ReligionService;
@SuppressWarnings("serial")
public abstract class GeneralInfoFormPanel extends BasePanel{
	private LocationService locationService = new LocationService();
	private EmployerService employerService = new EmployerService();
	private CountryService countryService = new CountryService();
	private Employer employer = getJESession().getUser().getEmployer();
	private JobCategoryService jobCategoryService = new JobCategoryService();
	
	public GeneralInfoFormPanel(String id) {
		super(id);
		
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);
		
		Form form = new Form("form",new CompoundPropertyModel(employer));
		add(form);
		
		FormComponent fc;
		
		fc = new TextField("name");
		fc.setRequired(true);
		form.add(fc);
		
		fc = new DropDownChoice("category",jobCategoryService.find(),new ChoiceRenderer("name","id"));
        fc.setRequired(true);
        form.add(fc);
		
		List<Country> countries = countryService.find();
        fc = new DropDownChoice("country",countries,new ChoiceRenderer("name","id"));
        fc.setRequired(true);
        form.add(fc);
		
		List<Location> locations = locationService.find();
        fc = new DropDownChoice("location",locations,new ChoiceRenderer("name","id"));
        fc.setRequired(true);
        form.add(fc);
        
        fc = new TextArea("address");
        fc.setRequired(true);
        form.add(fc);
        
        fc = new TextField("phone");
		form.add(fc);
        
        fc = new TextArea("description");
        fc.setRequired(true);
        form.add(fc);
	
		
        AjaxButton submit = new AjaxButton("submit"){

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				employer.getUser().setName(employer.getName());
				employer.getUser().setComplete(true);
				employerService.save(employer);
				getJESession().setUser(employer.getUser());
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
