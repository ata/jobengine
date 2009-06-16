package org.dynebolic.jobengine.page.administrator.group;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.dynebolic.jobengine.entity.Role;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;

public abstract class GroupFormPanel extends Panel{
	
	private IService<Role> service;
	private Role role = new Role();

	public GroupFormPanel(String id) {
		super(id);
		service = new GenericService<Role>(Role.class);
		role = new Role();
		Form form = new Form("groupForm");
		
		form.add(new AjaxButton("ajaxsubmit",form) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form arg1) {
				service.save(role);
				role.setName("");
				onSubmitForm(target);
				
			}
		});
		
		add(form);
		form.add(new TextField("name", new PropertyModel(role,"name")));
		form.setOutputMarkupId(true);
	}
	
	abstract protected void onSubmitForm(AjaxRequestTarget target);

}
