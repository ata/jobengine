/**
 * 
 */
package org.dynebolic.jobengine.page.administrator.generic;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.dynebolic.jobengine.entity.IEntity;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;

/**
 * @author ata
 * @param <Role>
 *
 */
@SuppressWarnings("serial")
public class GenericSortableDataProvider<E extends IEntity> extends SortableDataProvider {
	
	protected Class<?> domain;
	protected IService<E> service;
	
	public GenericSortableDataProvider(Class<?> domain){
		this.domain = domain;
		service = new GenericService<E>(this.domain);
		setSort("id", true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Iterator iterator(int first, int count) {
		SortParam sp = getSort();
		
		return service.find(first, count, sp.getProperty(), sp.isAscending()).iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public IModel model(Object object) {
		E e = (E) object;
		return new Model((Serializable) e);
	}

	@Override
	public int size() {
		return service.count().intValue();
	}

	public void setService(IService<E> service) {
		this.service = service;
	}
	
	
	
}