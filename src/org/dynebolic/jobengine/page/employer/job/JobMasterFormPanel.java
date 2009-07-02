package org.dynebolic.jobengine.page.employer.job;

import java.util.List;

import org.apache.log4j.Category;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.validation.validator.DateValidator;
import org.apache.wicket.validation.validator.NumberValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.dynebolic.jobengine.JobEngineAuthenticatedWebSession;
import org.dynebolic.jobengine.entity.Employer;
import org.dynebolic.jobengine.entity.EducationLevel;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.entity.JobCategory;
import org.dynebolic.jobengine.entity.Location;
import org.dynebolic.jobengine.entity.Role;
import org.dynebolic.jobengine.service.EducationLevelService;
import org.dynebolic.jobengine.service.EmployerService;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;
import org.dynebolic.jobengine.service.JobCategoryService;
import org.dynebolic.jobengine.service.JobService;
import org.dynebolic.jobengine.service.LocationService;

public abstract class JobMasterFormPanel extends Panel{
	
	private JobService service =  new JobService();
	private JobCategoryService categoryService = new JobCategoryService();
	private EducationLevelService educationLevelService = new EducationLevelService();
	private LocationService locationService = new LocationService();
	private EmployerService employerService = new EmployerService();
	private Job job;
	
	public JobMasterFormPanel(String id, final Long job_id) {
		super(id);
		if(job_id == null) {
			job = new Job();
			add(new Label("title","Add New Job"));
		} else {
			job = service.find(job_id);
			add(new Label("title","Edit Job"));
		}
		
		
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);

		Form form = new Form("form", new CompoundPropertyModel(job));
		add(form);
        form.setOutputMarkupId(true);
        
        FormComponent fc;
        
		//inisialisasi form component
        fc = new RequiredTextField("title");
        form.add(fc);
        
        fc = new TextArea("description");
        fc.setRequired(true);
        form.add(fc);
        
        ChoiceRenderer choiceRenderer = new ChoiceRenderer("name","id");
        
        List<JobCategory> categories = categoryService.find();
        fc = new DropDownChoice("category",categories,choiceRenderer);
        fc.setRequired(true);
        form.add(fc);
		
        fc = new RequiredTextField("position");
        form.add(fc);
        
        fc = new TextField("experience",Integer.class);
        fc.add(new NumberValidator.RangeValidator(1,50));
        form.add(fc);
        
        List<EducationLevel> educationLevels = educationLevelService.find();
        fc = new DropDownChoice("education",educationLevels,choiceRenderer);
        form.add(fc);
        
        fc = new TextField("ipk",Double.class);
        fc.add(new NumberValidator.DoubleRangeValidator(0.00,4.00));
        form.add(fc);
        
        fc = new TextField("salary",Long.class);
        fc.add(new NumberValidator.MinimumValidator(0));
        form.add(fc);
        
        fc = new DropDownChoice("country",employerService.find(),choiceRenderer);
        fc.setRequired(true);
        form.add(fc);
        
        List<Location> locations = locationService.find();
        fc = new DropDownChoice("location",locations,choiceRenderer);
        fc.setRequired(true);
        form.add(fc);

        fc = new TextArea("additionalSkills");
        form.add(fc);
        
        
        fc = new DateTextField("expired");
        fc.setRequired(true);
        fc.add(new DatePicker());
        form.add(fc);
        
        
		
		form.add(new AjaxButton("submit",form) {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				target.addComponent(feedback);
				System.out.println("User:" + ((JobEngineAuthenticatedWebSession) 
						JobEngineAuthenticatedWebSession.get()).getUser());
				
				Employer employer = ((JobEngineAuthenticatedWebSession) 
						JobEngineAuthenticatedWebSession.get()).getUser().getEmployer();
				System.out.println("Employer:" +employer);
				
				job.setEmployer(employer);
				service.save(job);
				onSubmitForm(target);
			}
			protected void onError(AjaxRequestTarget target, Form form) {
                target.addComponent(feedback);
            }
		});
		form.add(new AjaxLink("cancel"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				onCancel(target);
				
			}
			
		});
	}
	
	abstract protected void onSubmitForm(AjaxRequestTarget target);
	abstract protected void onCancel(AjaxRequestTarget target);

}
