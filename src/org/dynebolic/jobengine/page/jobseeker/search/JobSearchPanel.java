package org.dynebolic.jobengine.page.jobseeker.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.dynebolic.jobengine.entity.JobCategory;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.page.jobseeker.JobSeekerPage;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;

public class JobSearchPanel extends Panel {
	private static final long serialVersionUID = 2958087516678364254L;
	private Model model;
	public JobSearchPanel(String id, final BasePage page) {
		
		super(id);	
		//add(new JobSearchForm("searchForm"));
		Form form = new Form("searchForm");
		add(form);
		model = new Model();
		form.add (new TextField("keywords",model));
		
		AjaxButton submit = new AjaxButton("submit"){

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				String keywords = (String) model.getObject();
				Panel content = new JobSearchResultPanel("content", keywords, page);
				content.setOutputMarkupId(true);
				page.addOrReplace(content);
				target.addComponent(content);
				
			}
			
		};
		form.add(submit);
		
	
	}
	
}
