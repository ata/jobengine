package org.dynebolic.jobengine.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@SuppressWarnings("serial")
@Indexed
@Entity
@Table(name="job_seeker_cariers")
public class JobSeekerCarier implements IEntity{
	
	@DocumentId
	@Id
	@GeneratedValue
	private Long id;
	
	@Field
	private String institute;
	
	@Field
	private String position;
	
	private Integer start;
	
	private Integer done;
	
	@ManyToOne
	private JobSeeker jobSeeker;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getDone() {
		return done;
	}

	public void setDone(Integer done) {
		this.done = done;
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
	 * @param jobSeeker the jobSeeker to set
	 */
	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	/**
	 * @return the jobSeeker
	 */
	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	
	
	
}
