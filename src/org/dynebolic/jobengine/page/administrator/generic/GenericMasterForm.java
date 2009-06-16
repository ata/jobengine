package org.dynebolic.jobengine.page.administrator.generic;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.dynebolic.jobengine.entity.IEntity;
import org.dynebolic.jobengine.service.GenericService;
import org.dynebolic.jobengine.service.IService;

@SuppressWarnings("serial")
public class GenericMasterForm<E extends IEntity> extends Form{
	protected IService<E> service;
	protected E e;
	@SuppressWarnings("unchecked")
	public GenericMasterForm(String id, Class domain) {
		super(id);
		service = new GenericService<E>(domain);
		try {
			e = (E) domain.newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		add(new TextField("inputName", new PropertyModel(e,"name")));
	}
	protected void onSubmit(){
		service.save(e);
		e.setName("");
	}
	

}
