package org.dynebolic.jobengine.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="job_submiteds")
public class JobSubmited implements IEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Date dateSubmited = Calendar.getInstance().getTime();
	
	@ManyToOne
	private JobSeeker jobSeeker;
	
	@ManyToOne
	private Employer employer;
	
	private Boolean approved = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateSubmited() {
		return dateSubmited;
	}

	public void setDateSubmited(Date dateSubmited) {
		this.dateSubmited = dateSubmited;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
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
	
	
	
	
}
