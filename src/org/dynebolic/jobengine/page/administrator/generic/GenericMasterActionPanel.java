package org.dynebolic.jobengine.page.administrator.generic;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.entity.IEntity;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;

@SuppressWarnings("serial")
public class GenericMasterActionPanel<E extends IEntity> extends Panel{
	Class<?> domain;
	IService<E> service;
	
	public GenericMasterActionPanel(Class<?> domain, String componentId, final E e ) {
		super(componentId);
		this.domain = domain;
		service = new GenericService<E>(this.domain);
		Link deleteLink = new Link("deleteLink"){
			@Override
			public void onClick() {
				service.remove(e.getId());
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
