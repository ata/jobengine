package org.dynebolic.jobengine.page.jobseeker.profille;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.auth.account.AccountPanel;
import org.dynebolic.jobengine.page.jobseeker.JobSeekerPage;
import org.dynebolic.jobengine.page.jobseeker.preview.ApplicantPreviewPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.carier.CarierPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.certificate.CertificatePanel;
import org.dynebolic.jobengine.page.jobseeker.profille.education.EducationPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.general.GeneralInfoPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.language.LanguagePanel;
import org.dynebolic.jobengine.page.jobseeker.profille.picture.PictureUploadPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.skill.SkillPanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class ProfilePanel extends BasePanel{
	private ModalWindow modal = new ModalWindow("modal");
	public ProfilePanel(String id, JobSeekerPage page) {
		super(id);
		add(new GeneralInfoPanel("basicInfoPanel"));
		add(new EducationPanel("educationPanel"));
		add(new CertificatePanel("certificatePanel"));
		add(new CarierPanel("carierPanel"));
		add(new SkillPanel("skillPanel"));
		add(new LanguagePanel("languagePanel"));
		add(new PictureUploadPanel("uploadPanel",page));
		add(new AccountPanel("accountPanel"));
		add(modal);
		modal.setInitialWidth(780);
		modal.setResizable(false);
		modal.setCssClassName("w_silver");
		add(new AjaxLink("previewLink"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				modal.setContent(new AjaxLazyLoadPanel(modal.getContentId()){
					public Component getLazyLoadComponent(String id) {
						return new ApplicantPreviewPanel(
							id,getJESession().getUser().getJobSeeker().getId()){
							@Override
							protected void onClose(
									AjaxRequestTarget target) {
								modal.close(target);		
							}
						};
					}
					
				});
				modal.show(target);
				
			}
			
		});
	}
}
