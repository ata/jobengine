package org.dynebolic.jobengine.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="roles")
public class Role implements IEntity{

	@Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    

}
