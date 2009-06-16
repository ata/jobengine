package org.dynebolic.jobengine.page.employer.search;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class ApplicantSearchForm extends Form {

	private static final long serialVersionUID = -3385661855167754539L;
	private Model model;
	
	public ApplicantSearchForm(String id) {
		super(id);
		model = new Model();
		add(new TextField("keywords", model));
	}
	protected void onSubmit(){
		String keywords = (String) model.getObject();
	}

}
