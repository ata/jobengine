package org.dynebolic.jobengine.page;

import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.JobEngineAuthenticatedWebSession;

public class BasePanel extends Panel {

	public BasePanel(String id) {
		super(id);
	}
	public JobEngineAuthenticatedWebSession getJESession(){
		return  (JobEngineAuthenticatedWebSession) 
				JobEngineAuthenticatedWebSession.get();
	}
}
