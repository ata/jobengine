package org.dynebolic.jobengine.page.administrator.group;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.dynebolic.jobengine.entity.Role;
import org.dynebolic.jobengine.entity.Job;

import org.dynebolic.jobengine.hibernate.support.EMUtil;
import org.dynebolic.jobengine.page.administrator.AdministratorPage;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

public class GroupPage extends AdministratorPage{
	private IService<Role> service;
	private ModalWindow modal;
	DataTable grid;
	public GroupPage(){
		service = new GenericService<Role>(Role.class);
		
		AjaxLink addLink = new AjaxLink("addLink"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				modal.show(target);
				
			}
		};
		add(addLink);
		modal = new ModalWindow("modal");
		modal.setInitialWidth(270);
		modal.setInitialHeight(100);
		//modal.setRenderBodyOnly(true);
		modal.setResizable(false);
		modal.setCssClassName("w_silver");
		add(modal);
		
		GroupFormPanel panel = new GroupFormPanel(modal.getContentId()){

			@Override
			protected void onSubmitForm(AjaxRequestTarget target) {
				modal.close(target);
				target.addComponent(grid);
			}
			
		};
		
		modal.setContent(panel);
		add(getGrid());
		
		
	}
	
	@SuppressWarnings("serial")
	private DataTable getGrid(){
		List<IColumn> column = new ArrayList<IColumn>();
		column.add(new PropertyColumn(new Model("id"), "id","id"));
		column.add(new PropertyColumn(new Model("Name"), "name","name"));
		
		
		IColumn actionColumn = new AbstractColumn(new Model("Action")){
			@Override
			public void populateItem(Item cellItem, String componentId, final IModel rowModel) {
				Role role = (Role) rowModel.getObject();
				cellItem.add(new GroupActionPanel(componentId, role.getId(),grid));
			}

		};
		column.add(actionColumn);
		ISortableDataProvider dataProvider = new SortableGroupDataProvider();
		grid = 
			new AjaxFallbackDefaultDataTable("dataGrid", column, dataProvider,10);
		//add(grid);
		grid.setOutputMarkupId(true);
		return grid;
	}
}