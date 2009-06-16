package org.dynebolic.jobengine.page.administrator.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.service.JobService;

public class JobMasterGridPanel extends Panel {
	DataTable grid;
	ModalWindow modal = new ModalWindow("modal");
	JobService service = new JobService();
	
	
	
	public JobMasterGridPanel(String id) {
		this(id,new DefaultJobSortableDataProvider());
	}



	public JobMasterGridPanel(String id, ISortableDataProvider dataProvider) {
		super(id);
		
		AjaxLink addLink = new AjaxLink("addLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				modal.show(target);
			}
		};
		add(addLink);
		
		
		modal = new ModalWindow("modal");
		add(modal);
		
		JobMasterFormPanel panel = new JobMasterFormPanel(modal.getContentId(), null){

			@Override
			protected void onSubmitForm(AjaxRequestTarget target) {
				modal.close(target);
				target.addComponent(grid);
			}
			
		};
		
		modal.setContent(panel);
		
		
		
		List<IColumn> column = new ArrayList<IColumn>();
		column.add(new PropertyColumn(new Model("id"), "id","id"));
		column.add(new PropertyColumn(new Model("Position"), "position","position"));
		column.add(new PropertyColumn(new Model("Summary"), "summary","summary"));
		
		
		
		IColumn actionColumn = new AbstractColumn(new Model("Action")){
			@Override
			public void populateItem(Item cellItem, String componentId, final IModel rowModel) {
				Job job = (Job) rowModel.getObject();
				cellItem.add(new JobMasterActionPanel(componentId, job ,grid));
			}

		};
		
		column.add(actionColumn);
		
		grid =	new AjaxFallbackDefaultDataTable("dataGrid", column, dataProvider,10);
		grid.setOutputMarkupId(true);
		add(grid);
	}

}
