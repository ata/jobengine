package org.dynebolic.jobengine.page;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.JobEngineApplication;
import org.dynebolic.jobengine.JobEngineAuthenticatedWebSession;
import org.dynebolic.jobengine.page.auth.QuickSignInPanel;

public class BasePage extends WebPage implements IAjaxIndicatorAware{
	protected String ajaxIndicatorMarkupId = "ajaxBigIndicator";
	protected MenuPanel menuPanel;
	
	public BasePage(){
		// Javascript
		add(new WebMarkupContainer("prototypejs"));
		add(new WebMarkupContainer("scriptaculousjs"));
		add(new WebMarkupContainer("mainjs"));
		//CSS
		add(new WebMarkupContainer("resetcss"));
		add(new WebMarkupContainer("textcss"));
		add(new WebMarkupContainer("960css"));
		
		add(new WebMarkupContainer("glossymenucss"));
		add(new WebMarkupContainer("maincss"));
		//panel
		Panel quick = new QuickSignInPanel("quickSignInPanel");
		quick.setVisible(false);
		add(quick);
		menuPanel = new MenuPanel("menuPanel"); 
		add(menuPanel);
		
	}

	@Override
	public String getAjaxIndicatorMarkupId() {
		return ajaxIndicatorMarkupId;
	}
	
	public void setAjaxIndicatorMarkupId(String markupId){
		ajaxIndicatorMarkupId = markupId;
	}
	public JobEngineAuthenticatedWebSession getJESession(){
		return  (JobEngineAuthenticatedWebSession) 
				JobEngineAuthenticatedWebSession.get();
	}
}
