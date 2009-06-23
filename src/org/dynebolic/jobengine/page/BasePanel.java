package org.dynebolic.jobengine.page;

import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.JobEngineApplication;
import org.dynebolic.jobengine.JobEngineAuthenticatedWebSession;

public class BasePanel extends Panel {
	
	protected String uploadPath = ((JobEngineApplication)JobEngineApplication
			.get()).getUploadPath();
	
	public BasePanel(String id) {
		super(id);
	}
	public JobEngineAuthenticatedWebSession getJESession(){
		return  (JobEngineAuthenticatedWebSession) 
				JobEngineAuthenticatedWebSession.get();
	}
	public String getUploadPath() {
		return uploadPath;
	}
	
}
