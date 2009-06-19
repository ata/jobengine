package org.dynebolic.jobengine.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@SuppressWarnings("serial")
@Indexed
@Entity
@Table(name="job_seeker_educations")
public class JobSeekerEducation implements IEntity{
	
	@DocumentId
	@Id
	@GeneratedValue
	private Long id;
	
	@IndexedEmbedded
	@ManyToOne(targetEntity = EducationLevel.class)
	private EducationLevel educationLevel;
	
	private String faculty;
	
	private String start;
	
	private String done;
	
	@Field
	private Double ipk;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public Double getIpk() {
		return ipk;
	}

	public void setIpk(Double ipk) {
		this.ipk = ipk;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param faculty the faculty to set
	 */
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	/**
	 * @return the faculty
	 */
	public String getFaculty() {
		return faculty;
	}
	
	
	
	
}
