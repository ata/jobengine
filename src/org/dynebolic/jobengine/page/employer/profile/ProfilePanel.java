package org.dynebolic.jobengine.page.employer.profile;

import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.page.auth.account.AccountPanel;
import org.dynebolic.jobengine.page.employer.EmployerPage;
import org.dynebolic.jobengine.page.employer.profille.general.GeneralInfoPanel;
import org.dynebolic.jobengine.page.employer.profille.picture.PictureUploadPanel;

@SuppressWarnings("serial")
public class ProfilePanel extends Panel{

	public ProfilePanel(String id, EmployerPage page) {
		super(id);
		add(new GeneralInfoPanel("generalInfoPanel"));
		add(new PictureUploadPanel("uploadPanel",page));
		add(new AccountPanel("accountPanel"));
	}

}
