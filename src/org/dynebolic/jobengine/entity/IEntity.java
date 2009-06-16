package org.dynebolic.jobengine.entity;

import java.io.Serializable;

import org.apache.wicket.IClusterable;

public interface IEntity extends Serializable,IClusterable {
	public Long getId();
	public void setId(Long id);
	public String getName();
	public void setName(String name);
}
