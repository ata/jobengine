package org.dynebolic.jobengine.page.employer.search;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.library.AjaxLazyLoadPanel;

public class ApplicantSearchPanel extends Panel {
	private static final long serialVersionUID = 2958087516678364254L;
	private Component content;
	private Model model;
	@SuppressWarnings("serial")
	public ApplicantSearchPanel(String id, final BasePage page) {
		
		super(id);	
		//add(new JobSearchForm("searchForm"));
		Form form = new Form("searchForm");
		add(form);
		model = new Model();
		form.add (new TextField("keywords",model));
		
		AjaxButton submit = new AjaxButton("submit"){

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				final String keywords = (String) model.getObject();
				
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return new ApplicantSearchResultPanel("content", keywords, page);
					}
				};
				
				//Panel content = new ApplicantSearchResultPanel("content", keywords, page);
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
			
		};
		form.add(submit);
		
	
	}
	
}
