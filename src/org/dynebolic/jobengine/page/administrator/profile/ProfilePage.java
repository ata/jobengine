package org.dynebolic.jobengine.page.administrator.profile;

import org.dynebolic.jobengine.page.administrator.AdministratorPage;
import org.dynebolic.jobengine.page.auth.account.AccountPanel;

public class ProfilePage extends AdministratorPage {
	public ProfilePage() {
		add(new AccountPanel("accountPanel"));
	}
}
