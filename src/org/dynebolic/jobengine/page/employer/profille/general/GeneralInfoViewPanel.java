package org.dynebolic.jobengine.page.employer.profille.general;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.Employer;
import org.dynebolic.jobengine.page.BasePanel;

@SuppressWarnings("serial")
public abstract class GeneralInfoViewPanel extends BasePanel{
	private Employer employer = getJESession().getUser().getEmployer();
	public GeneralInfoViewPanel(String id) {
		super(id);
		
		System.out.println(getJESession().getUser());
		
		setModel(new CompoundPropertyModel(employer));
		
		add(new Label("name"));
		add(new Label("category.name"));
		add(new Label("country.name"));
		add(new Label("location.name"));
		add(new Label("address"));
		add(new Label("phone"));
		//add(new Label("description"));
		
		add(new Label("description", employer.getDescription()
				.replaceAll("\n", "<br/>")).setEscapeModelStrings(false));
		
		add(new AjaxLink("editLink") { 
			public void onClick(AjaxRequestTarget target) {
				onClickEdit(target);
			}
		});
	}
	
	protected abstract void onClickEdit(AjaxRequestTarget target);
	
}
