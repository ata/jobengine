package org.dynebolic.jobengine.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@SuppressWarnings("serial")
@Indexed
@Entity
@Table(name="job_seeker_skills")
public class JobSeekerSkill implements IEntity{  
	
	@DocumentId
	@Id
	@GeneratedValue
	private Long id;
	
	@Field
	private String name;
	
	@Field
	private Integer longTime;
	
	
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

	public Integer getLongTime() {
		return longTime;
	}

	public void setLongTime(Integer longTime) {
		this.longTime = longTime;
	}
	
	
	
	
}
