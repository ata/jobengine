package org.dynebolic.jobengine.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.dynebolic.jobengine.entity.IEntity;
import org.dynebolic.jobengine.hibernate.support.EMUtil;

@SuppressWarnings("serial")
public class GenericService<E extends IEntity> implements IService<E> {
	protected Class<?> _class;
	protected EntityManager em;
	protected Query query;
	
	public GenericService(Class<?> _class) {
		super();
		this._class = _class;
		
	}
	
	@SuppressWarnings("unchecked")
	public GenericService() {
		super();
		//this._class = Class.forName(getClass().getGenericSuperclass().toString().split("[<>]")[0]);
		final Type genericSuperclass = getClass().getGenericSuperclass();
		final ParameterizedType parameterizedType =
		((ParameterizedType)genericSuperclass);
		this._class = (Class<E>)parameterizedType.getActualTypeArguments()[0].getClass();
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E find(Long id) {
		em = EMUtil.createEntityManager();
		E e = (E) em.find(_class, id); 
		//em.close();
		return e;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> find(Integer first, Integer count, String orderColumn, Boolean ascending) {
		em = EMUtil.createEntityManager();
		StringBuilder squery = new StringBuilder("select e from ");
		squery.append(_class.getCanonicalName());
		squery.append(" e");
		squery.append(" order by").append(" e.").append(orderColumn);
		if(ascending) squery.append(" asc");
		else squery.append(" desc");
		query = em.createQuery(squery.toString());
		query.setFirstResult(first);
		query.setMaxResults(count);
		List<E> groups = query.getResultList();
		em.close();
		return groups;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> find() {
		em = EMUtil.createEntityManager();
		query = em.createQuery("from " + _class.getCanonicalName() + " e order by e.id");
		List<E> list = query.getResultList();
		em.close();
		return list;
	}

	@Override

	public void save(E object) {
		em = EMUtil.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(object);
		//em.refresh(object);
		em.flush();
		et.commit();
		em.close();
		
	}

	@Override
	public void save(List<E> list) {
		em = EMUtil.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		for (E object : list) {
			em.merge(object);
			//em.refresh(object);
			em.flush();
		}
		et.commit();
		em.close();
	}

	@Override
	public Long count() {
		em = EMUtil.createEntityManager();
		query = em.createQuery("select count(*) from " + _class.getCanonicalName());
		Long count = (Long) query.getSingleResult();
		em.close();
		return count;
	}

	@Override
	public Long size() {
		return count();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(Long id) {
		em = EMUtil.createEntityManager();
		E e = (E) em.find(_class, id); 
	    EntityTransaction et = em.getTransaction();
	    et.begin();
	    em.remove(e);
	    em.flush();
	    et.commit();
	    em.close();
	}

	@Override
	public void remove(E e) {
		remove(e.getId());
	}
}
