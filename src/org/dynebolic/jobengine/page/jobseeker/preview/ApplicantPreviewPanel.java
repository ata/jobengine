package org.dynebolic.jobengine.page.jobseeker.preview;

import java.text.DateFormat;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.Employer;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.JobSeekerCarier;
import org.dynebolic.jobengine.entity.JobSeekerCertificate;
import org.dynebolic.jobengine.entity.JobSeekerEducation;
import org.dynebolic.jobengine.entity.JobSeekerLanguage;
import org.dynebolic.jobengine.entity.JobSeekerSkill;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.message.MessagePanel;
import org.dynebolic.jobengine.service.EmployerService;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.library.AjaxLazyLoadPanel;
import org.dynebolic.library.PictureViewPanel;

@SuppressWarnings("serial")
public abstract class ApplicantPreviewPanel extends BasePanel {
	
	private JobSeekerService service = new JobSeekerService();
	private EmployerService employerService = new EmployerService();
	private JobSeeker jobSeeker;
	private DateFormat dateFormat =  DateFormat.getDateInstance(DateFormat.FULL);
	public ApplicantPreviewPanel(String id, Long jsId) {
		super(id);
		jobSeeker = service.find(jsId);
		personal();
		education();
		carier();
		certificate();
		skill();
		language();
		add(new AjaxLink("closeLink"){
			public void onClick(AjaxRequestTarget target) {
				onClose(target);
			}
		});
		
	}

	private void personal(){
		add(new PictureViewPanel("photo",jobSeeker.getPhoto(), 200, 200));
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
		
		add(new Label("category.name"));
		add(new Label("preferPosition"));
		
		add(new Label("description", jobSeeker.getDescription()
				.replaceAll("\n", "<br/>")).setEscapeModelStrings(false));
	}
	
	private void education(){
		ListView eachEducation = new ListView("eachEducation", jobSeeker.getEducations()){
			protected void populateItem(final ListItem item) {
				final JobSeekerEducation education = 
					(JobSeekerEducation) item.getModelObject();
				item.setOutputMarkupId(true);
				item.setModel(new CompoundPropertyModel(education));
				
				item.add(new Label("level.name"));
				item.add(new Label("faculty"));
				item.add(new Label("institute"));
				item.add(new Label("start"));
				item.add(new Label("done"));
				item.add(new Label("ipk"));
				
				
				Component lastLabel = null;
				if(education.isLast()){
					lastLabel = new Label("lastLabel","is last");
				} else {
					lastLabel = new Label("lastLabel","-");
				}
				item.add(lastLabel);
			}
		};
		add(eachEducation);
	}
	private void certificate(){
		ListView eachView = new ListView("eachCertificate", jobSeeker.getCertificates()){
			protected void populateItem(final ListItem item) {
				final JobSeekerCertificate certificate = 
					(JobSeekerCertificate) item.getModelObject();
				item.setModel(new CompoundPropertyModel(certificate));
				item.setOutputMarkupId(true);
				item.add(new Label("name"));
				item.add(new Label("year"));
			}
		};
		add(eachView);
	}
	
	private void carier(){
		ListView eachView = new ListView("eachCarier", jobSeeker.getCariers()){
			protected void populateItem(final ListItem item) {
				final JobSeekerCarier carier = (JobSeekerCarier) item.getModelObject();
				item.setModel(new CompoundPropertyModel(carier));
				item.setOutputMarkupId(true);
				item.add(new Label("institute"));
				item.add(new Label("position"));
				item.add(new Label("start"));
				item.add(new Label("done"));
			}
		};
		add(eachView);
	}
	
	private void skill(){
		ListView eachView = new ListView("eachSkill", jobSeeker.getSkills()){
			protected void populateItem(final ListItem item) {
				final JobSeekerSkill skill = (JobSeekerSkill) item.getModelObject();
				item.setModel(new CompoundPropertyModel(skill));
				item.setOutputMarkupId(true);
				item.add(new Label("name"));
				item.add(new Label("detail"));
				item.add(new Label("experience"));
			}
		};
		add(eachView);
	}
	
	private void language(){
		ListView eachView = new ListView("eachLanguage", jobSeeker.getLanguages()){
			protected void populateItem(final ListItem item) {
				final JobSeekerLanguage language = (JobSeekerLanguage) item.getModelObject();
				item.setModel(new CompoundPropertyModel(language));
				item.setOutputMarkupId(true);
				item.add(new Label("language.name"));
				item.add(new Label("level"));
			}
		};
		add(eachView);
	}
	
	protected abstract void onClose(AjaxRequestTarget target);
}
