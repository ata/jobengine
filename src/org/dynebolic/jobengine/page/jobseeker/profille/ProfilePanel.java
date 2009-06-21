package org.dynebolic.jobengine.page.jobseeker.profille;

import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.jobseeker.profille.basic.BasicInfoPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.experience.carier.CarierPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.experience.certificate.CertificatePanel;
import org.dynebolic.jobengine.page.jobseeker.profille.experience.education.EducationPanel;
import org.dynebolic.jobengine.page.jobseeker.profille.experience.language.LanguagePanel;
import org.dynebolic.jobengine.page.jobseeker.profille.experience.skill.SkillPanel;

@SuppressWarnings("serial")
public class ProfilePanel extends BasePanel{
	public ProfilePanel(String id) {
		super(id);
		add(new BasicInfoPanel("basicInfoPanel"));
		add(new EducationPanel("educationPanel"));
		add(new CertificatePanel("certificatePanel"));
		add(new CarierPanel("carierPanel"));
		add(new SkillPanel("skillPanel"));
		add(new LanguagePanel("languagePanel"));
	}
}
