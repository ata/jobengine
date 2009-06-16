package org.dynebolic.jobengine.page.employer.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.dynebolic.jobengine.entity.JobCategory;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;

public class ApplicantSearchPanel extends Panel {
	private static final long serialVersionUID = 2958087516678364254L;
	private IService<JobCategory> service;
	public ApplicantSearchPanel(String id) {
		
		super(id);	
		add(new ApplicantSearchForm("searchForm"));
	
	}
	
}
