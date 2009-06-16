package org.dynebolic.jobengine.service;

import java.io.Serializable;
import java.util.List;

public interface IService<E> extends Serializable{
	public List<E> find();
	public E find(Long i);
	public List<E> find(Integer first, Integer count, String orderColumn, Boolean ascending);
	public void save(E object);
	public void save(List<E> list);
	public void remove(Long id);
	public void remove(E e);
	public Long count();
	public Long size();
}
