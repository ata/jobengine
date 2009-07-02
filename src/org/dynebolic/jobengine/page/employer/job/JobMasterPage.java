package org.dynebolic.jobengine.page.employer.job;

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
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.entity.Role;
import org.dynebolic.jobengine.page.administrator.AdministratorPage;
import org.dynebolic.jobengine.page.administrator.group.GroupActionPanel;
import org.dynebolic.jobengine.page.administrator.group.GroupFormPanel;
import org.dynebolic.jobengine.page.administrator.group.SortableGroupDataProvider;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;

public class JobMasterPage extends AdministratorPage {	
	public JobMasterPage() {
		add(new JobMasterGridPanel("dataGrid"));
	}
}
