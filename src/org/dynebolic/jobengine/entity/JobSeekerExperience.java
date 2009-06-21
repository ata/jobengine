package org.dynebolic.jobengine.entity;

import javax.persistence.Column;
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
@Table(name="job_seeker_experiences")
public class JobSeekerExperience implements IEntity{
	
	@DocumentId
	@Id
	@GeneratedValue
	private Long id;
	
	@Field
	private String InstituteName;
	
	private Integer yearStart;
	
	private Integer yearDone;
	
	@Field
	private Integer longYear; 
	
	@Column(length=1000)
	private String description;
	
	@ManyToOne
	private JobSeeker jobSeeker;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstituteName() {
		return InstituteName;
	}

	public void setInstituteName(String instituteName) {
		InstituteName = instituteName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	 * @param longYear the longYear to set
	 */
	public void setLongYear(Integer longYear) {
		this.longYear = longYear;
	}

	/**
	 * @return the longYear
	 */
	public Integer getLongYear() {
		return longYear;
	}

	/**
	 * @param yearStart the yearStart to set
	 */
	public void setYearStart(Integer yearStart) {
		this.yearStart = yearStart;
	}

	/**
	 * @return the yearStart
	 */
	public Integer getYearStart() {
		return yearStart;
	}

	/**
	 * @param yearDone the yearDone to set
	 */
	public void setYearDone(Integer yearDone) {
		this.yearDone = yearDone;
	}

	/**
	 * @return the yearDone
	 */
	public Integer getYearDone() {
		return yearDone;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}
	
	
	
	
	
}
