package org.dynebolic.jobengine.page.jobseeker.profille.certificate;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.JobSeekerCarier;
import org.dynebolic.jobengine.entity.JobSeekerCertificate;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerCertificateService;
import org.dynebolic.library.JavascriptEventConfirmation;

@SuppressWarnings("serial")
public abstract class CertificateListPanel extends BasePanel{
	private JobSeekerCertificateService certificateService = 
		new JobSeekerCertificateService();
	private List<JobSeekerCertificate> certificates;
	
	public CertificateListPanel(String id) {
		this(id,false);
	}
	
	public CertificateListPanel(String id, Boolean reload) {
		super(id);
		if(reload){
			certificates = certificateService.find(getJESession()
				.getUser().getJobSeeker());
		} else {
			certificates = getJESession().getUser()
				.getJobSeeker().getCertificates();
		}
		
		final ListView eachView = new ListView("eachView", certificates){
			protected void populateItem(final ListItem item) {
				final JobSeekerCertificate certificate = 
					(JobSeekerCertificate) item.getModelObject();
				item.setModel(new CompoundPropertyModel(certificate));
				item.setOutputMarkupId(true);
				item.add(new Label("name"));
				item.add(new Label("year"));
				AjaxLink editLink = new AjaxLink("editLink"){
					public void onClick(AjaxRequestTarget target) {
						onEdit(target, certificate);
					}
					
				};
				
				item.add(editLink);
				AjaxLink deleteLink = new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						certificateService.remove(certificate);
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
	protected abstract void onEdit(AjaxRequestTarget target,JobSeekerCertificate certificate);
}
