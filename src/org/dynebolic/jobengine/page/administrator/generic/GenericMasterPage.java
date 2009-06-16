package org.dynebolic.jobengine.page.administrator.generic;


import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.dynebolic.jobengine.entity.IEntity;
import org.dynebolic.jobengine.page.administrator.AdministratorPage;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;


public class GenericMasterPage<E extends IEntity> extends AdministratorPage{
	IService<E> service;
	Class<?> domain;
	@SuppressWarnings("unchecked")
	public GenericMasterPage(Class<?> domain){
		add(new Label("title",domain.getSimpleName()));
		this.domain = domain;
		service = new GenericService<E>(domain);
		add(new GenericMasterForm("form",domain));
		doList();	
	}
	
	@SuppressWarnings("serial")
	private void doList(){
		List<IColumn> column = new ArrayList<IColumn>();
		column.add(new PropertyColumn(new Model("id"), "id","id"));
		column.add(new PropertyColumn(new Model("Name"), "name","name"));
		
		
		IColumn actionColumn = new AbstractColumn(new Model("Action")){
			@SuppressWarnings("unchecked")
			@Override
			public void populateItem(Item cellItem, String componentId, final IModel rowModel) {
				E e = (E) rowModel.getObject();
				cellItem.add(new GenericMasterActionPanel(domain,componentId, e));
			}

		};
		column.add(actionColumn);
		ISortableDataProvider dataProvider = new GenericSortableDataProvider<E>(domain);
		DataTable grid = 
			new AjaxFallbackDefaultDataTable("dataGrid", column, dataProvider,10);
		add(grid);
	}
}