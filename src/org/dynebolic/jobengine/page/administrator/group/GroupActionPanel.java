package org.dynebolic.jobengine.page.administrator.group;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.entity.Role;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;

@SuppressWarnings("serial")
public class GroupActionPanel extends Panel{
	
	IService<Role> service;
	
	public GroupActionPanel(String componentId, final Long entryId, final DataTable grid ) {
		super(componentId);
		service = new GenericService<Role>(Role.class);
		AjaxLink deleteLink = new AjaxLink("deleteLink"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				service.remove(entryId);
				target.addComponent(grid);
			}
		};
		add(deleteLink);
		Link editLink = new Link("editLink"){
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				
			}
		};
		add(editLink);
	}

}
