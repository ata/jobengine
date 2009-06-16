/**
 * 
 */
package org.dynebolic.jobengine.page.administrator.group;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.dynebolic.jobengine.entity.Role;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;

/**
 * @author ata
 * @param <GroupService>
 *
 */
public class SortableGroupDataProvider extends SortableDataProvider {
	
	private static final long serialVersionUID = 1L;
	IService<Role> service;
	
	public SortableGroupDataProvider(){
		service = new GenericService<Role>(Role.class);
		setSort("name", true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Iterator iterator(int first, int count) {
		SortParam sp = getSort();
		
		return service.find(first, count, sp.getProperty(), sp.isAscending()).iterator();
	}

	@Override
	public IModel model(Object object) {
		Role role = (Role) object;
		return new Model((Serializable) role);
	}

	@Override
	public int size() {
		return service.count().intValue();
	}
	
}